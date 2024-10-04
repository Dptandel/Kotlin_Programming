package com.tops.kotlin.hideshowtextviewoncheckapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.hideshowtextviewoncheckapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set a listener on the CheckBox
        binding.myCheckbox.setOnCheckedChangeListener { _, isChecked ->
            // Show or hide the TextView based on the CheckBox state
            binding.myTextView.visibility = if (isChecked) {
                TextView.VISIBLE
            } else {
                TextView.GONE
            }
        }
    }
}