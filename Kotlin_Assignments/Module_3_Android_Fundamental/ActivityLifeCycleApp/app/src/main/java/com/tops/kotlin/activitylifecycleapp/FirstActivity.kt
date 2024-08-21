package com.tops.kotlin.activitylifecycleapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FirstActivity : AppCompatActivity() {

    private lateinit var navigateToSecond : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("ACTIVITY-1", "onCreate: method called")

        setContentView(R.layout.activity_first)

        navigateToSecond = findViewById(R.id.navigateToSecond)

        navigateToSecond.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("ACTIVITY-1", "onStart: method called")
    }

    override fun onResume() {
        super.onResume()

        Log.d("ACTIVITY-1", "onResume: method called")
    }

    override fun onPause() {
        super.onPause()

        Log.d("ACTIVITY-1", "onPause: method called")
    }

    override fun onStop() {
        super.onStop()

        Log.d("ACTIVITY-1", "onStop: method called")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d("ACTIVITY-1", "onRestart: method called")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("ACTIVITY-1", "onDestroy: method called")
    }
}