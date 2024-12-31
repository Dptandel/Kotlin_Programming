package com.tops.kotlin.imageanimationapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.imageanimationapp.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val scaleUp = ObjectAnimator.ofFloat(binding.ivLogo, "scaleX", 1f, 1.5f, 1f).apply {
            duration = 1500
        }
        val scaleDown = ObjectAnimator.ofFloat(binding.ivLogo, "scaleY", 1f, 1.5f, 1f).apply {
            duration = 1500
        }

        AnimatorSet().apply {
            playTogether(scaleUp, scaleDown)
            start()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000) // Delay for 3 seconds
    }
}