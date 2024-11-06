package com.tops.kotlin.sqlitetodoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tops.kotlin.sqlitetodoapp.databinding.ItemTaskBinding

class TaskAdapter(private val tasks: List<Task>, private val onTaskSelected: (Task?) -> Unit) :
    Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(var binding: ItemTaskBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.binding.task.text = task.task
        holder.binding.completed.isChecked = task.isCompleted

        // Call onTaskSelected with the current task when checkbox is clicked
        holder.binding.completed.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                onTaskSelected(task)
            } else {
                onTaskSelected(null)  // Deselect the task if checkbox is unchecked
            }
        }
    }
}