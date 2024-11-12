package com.tops.kotlin.taskmanagementapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val date: String,
    val time: String,
    val priority: String,
    val isCompleted: Boolean = false
)