package com.tops.kotlin.studentmanagementsystemapp

import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.studentmanagementsystemapp.databinding.ActivityAddStudentBinding

class AddStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Dropdown menus for Course and City
        val courseOptions =
            arrayOf("Select Course","Web Development", "App Development", "Designing", "Networking", "Data Science")
        val cityOptions = arrayOf("Select City","Surat", "Navsari", "Valsad", "Vapi", "Bharuch")

        // Set up adapters for spinners
        binding.spinnerCourse.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, courseOptions)
        binding.spinnerCity.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cityOptions)

        // Set up filters and input types for specific fields
        binding.etContactNumber.apply {
            inputType = InputType.TYPE_CLASS_NUMBER
            filters = arrayOf(InputFilter.LengthFilter(10)) // Restrict to 10 digits max
        }

        // Submit button click listener
        binding.btnSubmit.setOnClickListener {
            try {
                // Validate first name and contact number with functions
                val firstName = validateFirstName(binding.etFirstName.text.toString())
                val contactNumber = validateContactNumber(binding.etContactNumber.text.toString())

                if (firstName != null && contactNumber != null) {
                    // Collect other form data if validations pass
                    val lastName = binding.etLastName.text.toString()
                    val email = binding.etEmail.text.toString()
                    val course = binding.spinnerCourse.selectedItem.toString()
                    val address = binding.etAddress.text.toString()
                    val pinCode = binding.etPincode.text.toString()
                    val city = binding.spinnerCity.selectedItem.toString()
                    val totalFees = binding.etTotalFees.text.toString()
                    val marks = binding.etMarks.text.toString()

                    // Process the form data (e.g., send to a server, store in local DB)
                    Toast.makeText(this, "Student details submitted", Toast.LENGTH_SHORT).show()
                } else {
                    // Clear all inputs and notify user to retry
                    clearForm(
                        binding.etFirstName,
                        binding.etLastName,
                        binding.etEmail,
                        binding.etAddress,
                        binding.etPincode,
                        binding.etTotalFees,
                        binding.etContactNumber,
                        binding.etMarks
                    )
                    Toast.makeText(
                        this,
                        "Invalid input, please enter details again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                // Handle any unexpected errors, notify user, and clear form
                Toast.makeText(
                    this,
                    "Unexpected error: ${e.message}. Please try again.",
                    Toast.LENGTH_LONG
                ).show()
                clearForm(
                    binding.etFirstName,
                    binding.etLastName,
                    binding.etEmail,
                    binding.etAddress,
                    binding.etPincode,
                    binding.etTotalFees,
                    binding.etContactNumber,
                    binding.etMarks
                )
            }
        }
    }

    // Function to validate first name
    private fun validateFirstName(input: String): String? {
        return if (input.isNotEmpty() && input.all { it.isLetter() }) {
            input // valid input
        } else {
            null // invalid input
        }
    }

    // Function to validate contact number
    private fun validateContactNumber(input: String): String? {
        return if (input.length == 10 && input.all { it.isDigit() }) {
            input // valid input
        } else {
            null // invalid input
        }
    }

    // Function to clear all form fields
    private fun clearForm(vararg fields: EditText) {
        fields.forEach { it.text.clear() }
    }

}