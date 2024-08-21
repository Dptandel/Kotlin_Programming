package com.tops.kotlin.fragmentlifecycleapp

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.fragmentlifecycleapp.fragments.FirstFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var manager = supportFragmentManager
        var transition = manager.beginTransaction()
        transition.add(R.id.fragmentContainer, FirstFragment())
        transition.commit()
    }
}