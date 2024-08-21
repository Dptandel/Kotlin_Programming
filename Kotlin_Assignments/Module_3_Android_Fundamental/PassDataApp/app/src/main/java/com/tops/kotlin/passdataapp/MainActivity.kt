package com.tops.kotlin.passdataapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var submit: AppCompatButton
    private lateinit var clear: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)
        submit = findViewById(R.id.submitBtn)
        clear = findViewById(R.id.clearBtn)

        val mobile : EditText = findViewById(R.id.mobile)
        val email : EditText = findViewById(R.id.email)

        submit.setOnClickListener {
            val fName = firstName.text.toString().trim()
            val lName = lastName.text.toString().trim()
            val mobile = mobile.text.toString().trim()
            val email = email.text.toString().trim()

            if (fName.isEmpty() || lName.isEmpty() || mobile.isEmpty() || email.isEmpty()) {
                Toast.makeText(this,"Enter all details properly..!!!",Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, DisplayActivity::class.java)
                intent.putExtra("firstName", fName)
                intent.putExtra("lastName", lName)
                intent.putExtra("mobile", mobile)
                intent.putExtra("email", email)
                startActivity(intent)
            }
        }

        clear.setOnClickListener {
            firstName.text.clear()
            lastName.text.clear()
            mobile.text.clear()
            email.text.clear()
        }
    }
}