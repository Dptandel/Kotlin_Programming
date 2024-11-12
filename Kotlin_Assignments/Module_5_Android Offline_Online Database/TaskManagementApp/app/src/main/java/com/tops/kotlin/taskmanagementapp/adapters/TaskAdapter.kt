package com.tops.kotlin.taskmanagementapp.adapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.kotlin.taskmanagementapp.databinding.TaskItemBinding
import com.tops.kotlin.taskmanagementapp.models.Task

class TaskAdapter(
    private var taskList: List<Task>,
    private val onTaskUpdate: (Task) -> Unit,
    private val onTaskDelete: (Task) -> Unit,
    private val onTaskComplete: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.taskName.text = task.name
            binding.taskDescription.text = task.description
            binding.taskDateTime.text = "${task.date} ${task.time}"

            val color = when {
                task.isCompleted -> Color.GRAY
                task.priority == "HIGH" -> Color.RED
                task.priority == "MEDIUM" -> Color.BLUE
                task.priority == "LOW" -> Color.GREEN
                else -> Color.BLACK
            }

            val textColor = when {
                task.isCompleted -> Color.WHITE
                task.priority == "HIGH" -> Color.WHITE
                task.priority == "MEDIUM" -> Color.WHITE
                task.priority == "LOW" -> Color.BLACK
                else -> Color.BLACK
            }

            binding.root.setBackgroundColor(color)

            binding.taskName.setTextColor(textColor)
            binding.taskDescription.setTextColor(textColor)

            // OnClick of Item Update and Delete Task
            binding.root.setOnClickListener {
                val options = arrayOf("Update Task", "Delete Task")
                AlertDialog.Builder(binding.root.context)
                    .setItems(options) { dialog, which ->
                        when (which) {
                            0 -> onTaskUpdate(task) // Update task
                            1 -> onTaskDelete(task)   // Delete task
                        }
                    }
                    .show()
            }

            // Long-click listener to show options for Update and Delete
            binding.root.setOnLongClickListener {
                val options = arrayOf("Complete Task")
                AlertDialog.Builder(binding.root.context)
                    .setItems(options) { dialog, which ->
                        when (which) {
                            0 -> onTaskComplete(task)   // Complete task
                        }
                    }
                    .show()
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateTasks(newTasks: List<Task>) {
        taskList = newTasks
        notifyDataSetChanged() // Notify RecyclerView to refresh it
    }
}