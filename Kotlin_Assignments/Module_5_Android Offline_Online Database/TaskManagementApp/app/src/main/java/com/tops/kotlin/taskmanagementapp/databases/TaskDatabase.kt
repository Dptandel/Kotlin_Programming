package com.tops.kotlin.taskmanagementapp.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tops.kotlin.taskmanagementapp.daos.TaskDao
import com.tops.kotlin.taskmanagementapp.models.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).allowMainThreadQueries() // Allow queries on the main thread
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}