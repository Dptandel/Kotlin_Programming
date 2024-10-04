package com.tops.kotlin.incdecfontsizeapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.incdecfontsizeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentSize: Float = 16f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIncrease.setOnClickListener {
            currentSize += 2f // Increase size by 2sp
            binding.textView.textSize = currentSize
        }

        binding.btnDecrease.setOnClickListener {
            if (currentSize > 2f) { // Prevent size from going too small
                currentSize -= 2f // Decrease size by 2sp
                binding.textView.textSize = currentSize
            }
        }
    }
}