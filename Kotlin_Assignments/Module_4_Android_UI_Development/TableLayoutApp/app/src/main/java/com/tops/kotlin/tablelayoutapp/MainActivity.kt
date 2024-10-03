package com.tops.kotlin.tablelayoutapp

import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import com.tops.kotlin.tablelayoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var textViewCount = 0 // To keep track of the number of TextViews added

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addTextViewButton.setOnClickListener {
            addTextView()
        }
    }

    private fun addTextView() {
        // Create a new TableRow
        val tableRow = TableRow(this)

        // Create a new TextView
        val textView = TextView(this).apply {
            text = "TextView ${++textViewCount}" // Increment and display the count
            setPadding(24) // Add some padding
            textSize = 20f // Set text size
        }

        // Add TextView to the TableRow
        tableRow.addView(textView)

        // Add TableRow to the TableLayout
        binding.tableLayout.addView(tableRow)
    }
}