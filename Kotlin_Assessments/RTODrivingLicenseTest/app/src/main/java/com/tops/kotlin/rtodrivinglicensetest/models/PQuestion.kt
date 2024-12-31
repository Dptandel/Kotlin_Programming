package com.tops.kotlin.rtodrivinglicensetest.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pquestions")
data class PQuestion(
    @PrimaryKey(autoGenerate = true) val no: Int,
    val question: String? = null,
    val option_1: String? = null,
    val option_2: String? = null,
    val option_3: String? = null,
    val picture: String? = null,
    val answer: Int? = null,
    val sign: String? = null
)
