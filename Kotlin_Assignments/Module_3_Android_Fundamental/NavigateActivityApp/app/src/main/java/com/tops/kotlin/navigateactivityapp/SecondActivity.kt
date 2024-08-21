package com.tops.kotlin.navigateactivityapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val secondToThirdBtn : Button = findViewById(R.id.secondToThirdBtn)
        val backToFirstBtn : Button = findViewById(R.id.backToFirstBtn)

        secondToThirdBtn.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        backToFirstBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}