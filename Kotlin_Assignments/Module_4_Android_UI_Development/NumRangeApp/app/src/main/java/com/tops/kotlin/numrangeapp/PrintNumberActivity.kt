package com.tops.kotlin.numrangeapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PrintNumberActivity : AppCompatActivity() {

    private lateinit var numbers: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_print_number)

        numbers = findViewById(R.id.numbers)

        val intent = intent
        val numStart = intent.getIntExtra("START", 0)
        val numEnd = intent.getIntExtra("END", 0)

        val (start, end) = if (numStart > numEnd) numEnd to numStart else numStart to numEnd

        val result = StringBuilder("Numbers between $start and $end are \n")
        for (i in (start + 1) until end) {
            result.append(i).append(" ")
        }
        numbers.text = result.toString()
    }
}