package com.tops.kotlin.passdataapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DisplayActivity : AppCompatActivity() {

    private lateinit var txtName : TextView

    private lateinit var btnBack : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        txtName = findViewById(R.id.txtName)
        btnBack = findViewById(R.id.backBtn)

        val txtMobile : TextView = findViewById(R.id.txtMobile)
        val txtEmail : TextView = findViewById(R.id.txtEmail)

        val intent = intent
        val fName = intent.getStringExtra("firstName")
        val lName = intent.getStringExtra("lastName")
        val mobile = intent.getStringExtra("mobile")
        val email = intent.getStringExtra("email")

        txtName.text = "$fName $lName"
        txtMobile.text = mobile
        txtEmail.text = email

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}