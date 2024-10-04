package com.tops.kotlin.generateedittextapp

import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.generateedittextapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateButton.setOnClickListener {
            generateEditTexts()
        }
    }

    private fun generateEditTexts() {
        // Clear previous EditTexts
        binding.editTextContainer.removeAllViews()

        val numberInput = binding.inputNumber.text.toString()
        val n = numberInput.toIntOrNull()

        if (n != null && n > 0) {
            for (i in 1..n) {
                val editText = EditText(this)
                editText.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                editText.hint = "EditText $i"
                binding.editTextContainer.addView(editText)
            }
        } else {
            Toast.makeText(this, "Please enter a valid number greater than 0", Toast.LENGTH_SHORT)
                .show()
        }
    }
}