package com.tops.kotlin.navigateactivityapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val backToSecondBtn : Button = findViewById(R.id.backToSecondBtn)

        backToSecondBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}