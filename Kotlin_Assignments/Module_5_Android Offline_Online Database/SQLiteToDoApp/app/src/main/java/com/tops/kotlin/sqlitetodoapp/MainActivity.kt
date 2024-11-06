package com.tops.kotlin.sqlitetodoapp

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.kotlin.sqlitetodoapp.databinding.ActivityMainBinding
import com.tops.kotlin.sqlitetodoapp.databinding.DialogAddTaskBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var dbHelper: TaskDBHelper
    private lateinit var adapter: TaskAdapter

    private var selectedTask: Task? = null // Holds the selected task for updating/deleting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = TaskDBHelper(this)

        // Fetch tasks and initialize RecyclerView
        refreshTasks()

        binding.btnAddTask.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)
            dialog.setView(dialogBinding.root)

            dialog.setTitle("Add New Task")

            dialog.setPositiveButton("Add") { dialog, _ ->
                val taskTitle = dialogBinding.edtTask.text.toString()
                if (taskTitle.isNotEmpty()) {
                    val newTask = Task(task = taskTitle)
                    dbHelper.addTask(newTask)
                    refreshTasks()  // Reload tasks to reflect the new task
                    dialog.dismiss()
                }
            }

            dialog.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

            val alertDialog = dialog.create()
            alertDialog.show()
        }

        // Delete Task Button listener
        binding.btnDeleteTask.setOnClickListener {
            selectedTask?.let { task ->
                dbHelper.deleteTask(task.id)
                refreshTasks()
                hideActionButtons()  // Hide buttons after deletion
            }
        }

        // Update Task Button listener
        binding.btnUpdateTask.setOnClickListener {
            selectedTask?.let { task ->
                showUpdateTaskDialog(task)
            }
        }
    }

    private fun refreshTasks() {
        val tasks = dbHelper.getAllTasks()
        adapter = TaskAdapter(tasks) { task ->
            selectedTask = task
            if (task != null) {
                showActionButtons()
            } else {
                hideActionButtons()
            }
        }
        binding.rvTasks.layoutManager = LinearLayoutManager(this)
        binding.rvTasks.adapter = adapter
    }

    private fun showUpdateTaskDialog(task: Task) {
        val dialogBuilder = AlertDialog.Builder(this)
        val dialogViewBinding = DialogAddTaskBinding.inflate(layoutInflater)
        dialogBuilder.setView(dialogViewBinding.root)

        dialogViewBinding.edtTask.setText(task.task)  // Set current task in the dialog

        dialogBuilder.setTitle("Update Task")

        dialogBuilder.setPositiveButton("Update") { dialog, _ ->
            val updatedTitle = dialogViewBinding.edtTask.text.toString()
            if (updatedTitle.isNotEmpty()) {
                val updatedTask = task.copy(task = updatedTitle)
                dbHelper.updateTask(updatedTask)
                refreshTasks()
                hideActionButtons()  // Hide buttons after update
                dialog.dismiss()
            }
        }

        dialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    private fun showActionButtons() {
        findViewById<LinearLayout>(R.id.actionBtnsLayout).visibility = View.VISIBLE
    }

    private fun hideActionButtons() {
        findViewById<LinearLayout>(R.id.actionBtnsLayout).visibility = View.GONE
        selectedTask = null  // Clear the selected task when hiding action buttons
    }
}