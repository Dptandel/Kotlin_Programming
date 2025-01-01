package com.tops.kotlin.rtodrivinglicensetest.daos

import androidx.room.Dao
import androidx.room.Query
import com.tops.kotlin.rtodrivinglicensetest.models.Question

@Dao
interface QuestionDao {
    @Query("SELECT * FROM pquestions")
    fun getAllQuestions(): List<Question>

    @Query("SELECT * FROM pquestions ORDER BY RANDOM()")
    fun getRandomQuestions(): List<Question>
}