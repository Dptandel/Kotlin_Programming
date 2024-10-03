package com.tops.kotlin.radiobuttonapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tops.kotlin.radiobuttonapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun calculateResult(view: View) {
        val number1 = binding.edtNumber1.text.toString().toDoubleOrNull()
        val number2 = binding.edtNumber2.text.toString().toDoubleOrNull()

        if (number1 == null || number2 == null) {
            Toast.makeText(this, "Enter numbers first...!", Toast.LENGTH_SHORT).show()
            return
        } else {
            val selectedOperationId = binding.radioGroup.checkedRadioButtonId

            if (selectedOperationId == -1) {
                // No operation selected
                Toast.makeText(this, "Please select an operation", Toast.LENGTH_SHORT).show()
                return
            } else {
                // Operation selected
                val result = when (selectedOperationId) {
                    binding.additionBtn.id -> "Addition : ${number1 + number2}"
                    binding.subtractionBtn.id -> "Subtraction : ${number1 - number2}"
                    binding.multiplicationBtn.id -> "Multiplication : ${number1 * number2}"
                    binding.divisionBtn.id -> {
                        if (number2 != 0.0) {
                            "Division : ${number1 / number2}"
                        } else {
                            Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                            return
                        }
                    }
                    else -> {
                        Toast.makeText(this, "Unexpected error", Toast.LENGTH_SHORT).show()
                        return
                    }
                }
                binding.tvResult.text = result
            }
        }
    }
}