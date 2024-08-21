package com.tops.kotlin.buttonclickapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView)
        val main_bg : LinearLayout = findViewById(R.id.main_bg)

        val redBtn: AppCompatButton = findViewById(R.id.redBtn)
        redBtn.setOnClickListener {
            main_bg.setBackgroundColor(Color.RED)
            textView.text = "Selected : Red"
        }

        val greenBtn: AppCompatButton = findViewById(R.id.greenBtn)
        greenBtn.setOnClickListener {
            main_bg.setBackgroundColor(Color.GREEN)
            textView.text = "Selected : Green"
        }

        val blueBtn: AppCompatButton = findViewById(R.id.blueBtn)
        blueBtn.setOnClickListener {
            main_bg.setBackgroundColor(Color.BLUE)
            textView.text = "Selected : Blue"
        }

        val clrBtn: Button = findViewById(R.id.clrBtn)
        clrBtn.setOnClickListener {
            main_bg.setBackgroundColor(Color.WHITE)
            textView.text = "Click to see the magic"
        }
    }
}