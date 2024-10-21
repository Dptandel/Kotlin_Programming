package com.tops.kotlin.toolbarspinnersearchapp

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tops.kotlin.toolbarspinnersearchapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up Spinner
        val items = arrayOf("Top News", "Business", "Politics", "Sports", "Technology", "Movies")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        // Set up Search Button click listener
        binding.searchButton.setOnClickListener {
            performSearch()
        }
    }

    private fun performSearch() {
        // Show the loading spinner
        binding.progressBar.visibility = View.VISIBLE

        // Simulate a network operation or processing
        binding.searchInput.isEnabled = false
        binding.searchButton.isEnabled = false

        // Add delay for demonstration purposes
        binding.searchInput.postDelayed({
            // Hide the loading spinner
            binding.progressBar.visibility = View.GONE

            // Enable inputs again
            binding.searchInput.isEnabled = true
            binding.searchButton.isEnabled = true

            // handle the search results here
            // For demonstration, just showing a log
            val searchText = binding.searchInput.text.toString()
            Toast.makeText(this, "Searching for: $searchText", Toast.LENGTH_SHORT).show()
        }, 2000) // Simulated delay
    }
}
