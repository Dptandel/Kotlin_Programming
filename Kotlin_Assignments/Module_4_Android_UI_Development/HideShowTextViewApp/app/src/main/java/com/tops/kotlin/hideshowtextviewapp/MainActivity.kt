package com.tops.kotlin.hideshowtextviewapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.hideshowtextviewapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listeners
        binding.btnHide.setOnClickListener {
            binding.tvName.visibility = TextView.INVISIBLE
        }

        binding.btnShow.setOnClickListener {
            binding.tvName.visibility = TextView.VISIBLE
        }
    }
}