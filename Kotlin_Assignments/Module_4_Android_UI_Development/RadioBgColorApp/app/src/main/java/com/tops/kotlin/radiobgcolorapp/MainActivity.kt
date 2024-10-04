package com.tops.kotlin.radiobgcolorapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.radiobgcolorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.colorRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.red_radio -> binding.colorDisplay.setBackgroundColor(Color.RED)
                R.id.green_radio -> binding.colorDisplay.setBackgroundColor(Color.GREEN)
                R.id.blue_radio -> binding.colorDisplay.setBackgroundColor(Color.BLUE)
            }
        }
    }
}