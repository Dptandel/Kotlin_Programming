package com.tops.kotlin.sqlitetodoapp

data class Task(
    val id: Int = 0,
    val task: String = "",
    var isCompleted: Boolean = false
)
