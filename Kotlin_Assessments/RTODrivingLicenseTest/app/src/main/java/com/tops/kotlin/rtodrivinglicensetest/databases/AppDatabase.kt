package com.tops.kotlin.rtodrivinglicensetest.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tops.kotlin.rtodrivinglicensetest.daos.QuestionDao
import com.tops.kotlin.rtodrivinglicensetest.models.Question

@Database(entities = [Question::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPQDao(): QuestionDao
}