package com.tops.kotlin.numrangeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtNumStart: EditText
    private lateinit var edtNumEnd: EditText
    private lateinit var btnPrint: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNumStart = findViewById(R.id.edtNumStart)
        edtNumEnd = findViewById(R.id.edtNumEnd)
        btnPrint = findViewById(R.id.btnPrint)

        btnPrint.setOnClickListener {
            val numStart = edtNumStart.text.toString().toInt()
            val numEnd = edtNumEnd.text.toString().toInt()

            val intent = Intent(this, PrintNumberActivity::class.java)
            intent.putExtra("START", numStart)
            intent.putExtra("END", numEnd)
            startActivity(intent)
        }
    }
}