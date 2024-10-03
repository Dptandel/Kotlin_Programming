package com.tops.kotlin.seekbarapp

import android.graphics.Color
import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.seekbarapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val colorChangeListener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateBackgroundColor()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        }

        binding.seekBarRed.setOnSeekBarChangeListener(colorChangeListener)
        binding.seekBarGreen.setOnSeekBarChangeListener(colorChangeListener)
        binding.seekBarBlue.setOnSeekBarChangeListener(colorChangeListener)
    }

    private fun updateBackgroundColor() {
        val red = binding.seekBarRed.progress
        val green = binding.seekBarGreen.progress
        val blue = binding.seekBarBlue.progress
        binding.layout.setBackgroundColor(Color.rgb(red, green, blue))
    }
}