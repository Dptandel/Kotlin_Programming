package com.tops.kotlin.activitylifecycleapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

    private lateinit var backToFirstBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("ACTIVITY-2", "onCreate: method called")

        setContentView(R.layout.activity_second)

        backToFirstBtn = findViewById(R.id.backToFirstBtn)

        backToFirstBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("ACTIVITY-2", "onStart: method called")
    }

    override fun onResume() {
        super.onResume()

        Log.d("ACTIVITY-2", "onResume: method called")
    }

    override fun onPause() {
        super.onPause()

        Log.d("ACTIVITY-2", "onPause: method called")
    }

    override fun onStop() {
        super.onStop()

        Log.d("ACTIVITY-2", "onStop: method called")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d("ACTIVITY-2", "onRestart: method called")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("ACTIVITY-2", "onDestroy: method called")
    }
}