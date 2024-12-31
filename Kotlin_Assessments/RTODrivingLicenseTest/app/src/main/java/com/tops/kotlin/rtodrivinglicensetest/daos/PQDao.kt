package com.tops.kotlin.rtodrivinglicensetest.daos

import androidx.room.Dao
import androidx.room.Query
import com.tops.kotlin.rtodrivinglicensetest.models.PQuestion

@Dao
interface PQDao {
    @Query("SELECT * FROM pquestions")
    fun getAllQuestions(): List<PQuestion>
}