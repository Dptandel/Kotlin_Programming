package com.tops.kotlin.rtodrivinglicensetest.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tops.kotlin.rtodrivinglicensetest.daos.PQDao
import com.tops.kotlin.rtodrivinglicensetest.models.PQuestion

@Database(entities = [PQuestion::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPQDao(): PQDao
}