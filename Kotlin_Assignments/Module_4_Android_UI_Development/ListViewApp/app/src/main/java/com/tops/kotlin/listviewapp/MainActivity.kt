package com.tops.kotlin.listviewapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.listviewapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the string array from resources
        val myStringArray = resources.getStringArray(R.array.my_string_array)

        // Create an ArrayAdapter using the string array and a default list item layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, myStringArray)

        // Set the adapter to the ListView
        binding.myListView.adapter = adapter

        binding.myListView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as String
            binding.tvListView.text = selectedItem
            Toast.makeText(this, selectedItem, Toast.LENGTH_SHORT).show()
        }
    }
}