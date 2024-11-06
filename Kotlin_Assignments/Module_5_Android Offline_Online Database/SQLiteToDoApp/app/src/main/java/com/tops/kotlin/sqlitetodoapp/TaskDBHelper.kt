package com.tops.kotlin.sqlitetodoapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TaskDBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "todo.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "tasks"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "task"
        private const val COLUMN_IS_COMPLETED = "is_completed"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable =
            ("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_TITLE TEXT, $COLUMN_IS_COMPLETED INTEGER)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Add task
    fun addTask(task: Task): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_TITLE, task.task)
            put(COLUMN_IS_COMPLETED, if (task.isCompleted) 1 else 0)
        }
        return db.insert(TABLE_NAME, null, contentValues)
    }

    // Get all tasks
    fun getAllTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        val db = this.readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val task = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val isCompleted = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_COMPLETED)) == 1
            tasks.add(Task(id, task, isCompleted))
        }
        cursor.close()
        return tasks
    }

    // Update task
    fun updateTask(task: Task): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_TITLE, task.task)
            put(COLUMN_IS_COMPLETED, if (task.isCompleted) 1 else 0)
        }
        return db.update(TABLE_NAME, contentValues, "$COLUMN_ID=?", arrayOf(task.id.toString()))
    }

    // Delete task
    fun deleteTask(taskId: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(taskId.toString()))
    }
}