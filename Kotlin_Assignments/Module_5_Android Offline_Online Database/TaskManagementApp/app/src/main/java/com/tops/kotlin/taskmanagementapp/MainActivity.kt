package com.tops.kotlin.taskmanagementapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.kotlin.taskmanagementapp.adapters.TaskAdapter
import com.tops.kotlin.taskmanagementapp.databases.TaskDatabase
import com.tops.kotlin.taskmanagementapp.databinding.ActivityMainBinding
import com.tops.kotlin.taskmanagementapp.databinding.DialogAddTaskBinding
import com.tops.kotlin.taskmanagementapp.models.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var db: TaskDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDatabase.getDatabase(this)

        val taskList = db.taskDao().getAllTasks()
        taskAdapter = TaskAdapter(taskList, onTaskUpdate = { task ->
            showUpdateTaskDialog(task)
        }, onTaskDelete = { task ->
            deleteTask(task)
        }, onTaskComplete = { task ->
            markTaskAsCompleted(task)
        })


        // Set up RecyclerView
        binding.taskRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.taskRecyclerView.adapter = taskAdapter

        binding.addTaskButton.setOnClickListener {
            //show dialog to add task
            showAddTaskDialog()
        }
    }

    private fun markTaskAsCompleted(task: Task) {
        val updatedTask = task.copy(isCompleted = true)
        db.taskDao().updateTask(updatedTask)
        refreshTaskList()
    }

    @SuppressLint("InflateParams")
    private fun showAddTaskDialog() {
        val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)

        dialogBinding.taskDateEditText.setOnClickListener {
            // Get the current date
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Create a DatePickerDialog instance
            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Format the selected date as "dd/MM/yyyy"
                    val selectedDate = Calendar.getInstance().apply {
                        set(selectedYear, selectedMonth, selectedDay)
                    }
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    dialogBinding.taskDateEditText.setText(dateFormat.format(selectedDate.time))
                },
                year, month, day
            )

            // Show the date picker dialog
            datePickerDialog.show()
        }

        dialogBinding.taskTimeEditText.setOnClickListener {
            // Get the current time
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            // Create a TimePickerDialog instance
            val timePickerDialog = TimePickerDialog(
                this,
                { _, selectedHour, selectedMinute ->
                    // Format the selected time as "HH:mm"
                    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                    val selectedTime = Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, selectedHour)
                        set(Calendar.MINUTE, selectedMinute)
                    }
                    dialogBinding.taskTimeEditText.setText(timeFormat.format(selectedTime.time))
                },
                hour, minute, true
            )

            // Show the time picker dialog
            timePickerDialog.show()
        }

        val dialog = AlertDialog.Builder(this)
            .setTitle("Add Task")
            .setView(dialogBinding.root)
            .setPositiveButton("Add", null) // Set to null to handle manually
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()

        // Handle Positive Button manually
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val name = dialogBinding.taskNameEditText.text.toString().trim()
            val description = dialogBinding.taskDescriptionEditText.text.toString().trim()
            val date = dialogBinding.taskDateEditText.text.toString().trim()
            val time = dialogBinding.taskTimeEditText.text.toString().trim()

            // Validate fields
            if (name.isEmpty() || description.isEmpty() || date.isEmpty() || time.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                val priority = dialogBinding.taskPrioritySpinner.selectedItem.toString()
                val newTask = Task(
                    name = name,
                    description = description,
                    date = date,
                    time = time,
                    priority = priority
                )
                db.taskDao().insertTask(newTask)
                refreshTaskList()
                dialog.dismiss() // Close the dialog only after successful validation
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun showUpdateTaskDialog(task: Task) {
        val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)

        // Pre-populate the dialog fields with the current task details
        dialogBinding.taskNameEditText.setText(task.name)
        dialogBinding.taskDescriptionEditText.setText(task.description)
        dialogBinding.taskDateEditText.setText(task.date)
        dialogBinding.taskTimeEditText.setText(task.time)
        val priorityPosition =
            resources.getStringArray(R.array.priority_levels).indexOf(task.priority)
        dialogBinding.taskPrioritySpinner.setSelection(priorityPosition)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Update Task")
            .setView(dialogBinding.root)
            .setPositiveButton("Update", null) // Set to null to handle manually
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()

        // Handle Positive Button manually
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val name = dialogBinding.taskNameEditText.text.toString().trim()
            val description = dialogBinding.taskDescriptionEditText.text.toString().trim()
            val date = dialogBinding.taskDateEditText.text.toString().trim()
            val time = dialogBinding.taskTimeEditText.text.toString().trim()

            // Validate fields
            if (name.isEmpty() || description.isEmpty() || date.isEmpty() || time.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                val priority = dialogBinding.taskPrioritySpinner.selectedItem.toString()
                val updatedTask = task.copy(
                    name = name,
                    description = description,
                    date = date,
                    time = time,
                    priority = priority
                )
                lifecycleScope.launch(Dispatchers.IO) {
                    db.taskDao().updateTask(updatedTask)
                    withContext(Dispatchers.Main) {
                        refreshTaskList()
                        dialog.dismiss() // Close the dialog after successful update
                    }
                }
            }
        }
    }

    private fun deleteTask(task: Task) {
        lifecycleScope.launch(Dispatchers.IO) {
            db.taskDao().deleteTask(task)
            withContext(Dispatchers.Main) {
                refreshTaskList()
            }
        }
    }

    private fun refreshTaskList() {
        val updatedTaskList = db.taskDao().getAllTasks()
        taskAdapter.updateTasks(updatedTaskList)
    }
}