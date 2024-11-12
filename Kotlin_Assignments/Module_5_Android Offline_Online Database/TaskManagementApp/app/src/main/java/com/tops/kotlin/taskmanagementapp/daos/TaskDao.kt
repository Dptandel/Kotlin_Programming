package com.tops.kotlin.taskmanagementapp.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tops.kotlin.taskmanagementapp.models.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("SELECT * FROM task_table ORDER BY date ASC, time ASC")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM task_table WHERE date = :date")
    fun getTasksByDate(date: String): List<Task>
}