package com.tops.kotlin.rtodrivinglicensetest.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pquestions")
data class Question(
    @PrimaryKey
    @ColumnInfo(name = "no")
    val no: Int,

    @ColumnInfo(name = "question")
    val question: String,

    @ColumnInfo(name = "option_1")
    val option1: String,

    @ColumnInfo(name = "option_2")
    val option2: String,

    @ColumnInfo(name = "option_3")
    val option3: String,

    @ColumnInfo(name = "picture")
    val picture: String = "Image Not Found",

    @ColumnInfo(name = "answer")
    val answer: Int,

    @ColumnInfo(name = "sign")
    val sign: String? = null
)
