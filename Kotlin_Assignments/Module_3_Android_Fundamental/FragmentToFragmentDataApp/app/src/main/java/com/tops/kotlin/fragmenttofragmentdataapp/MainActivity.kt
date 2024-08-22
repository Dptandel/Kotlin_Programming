package com.tops.kotlin.fragmenttofragmentdataapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.fragmenttofragmentdataapp.fragments.Communicator
import com.tops.kotlin.fragmenttofragmentdataapp.fragments.DetailsFragment

class MainActivity : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun sendDetails(firstName: String, lastName: String, mobile: String, email: String) {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer2)

        if (fragment is DetailsFragment) {
            fragment.displayDetails(firstName, lastName, mobile, email)
        }
    }
}