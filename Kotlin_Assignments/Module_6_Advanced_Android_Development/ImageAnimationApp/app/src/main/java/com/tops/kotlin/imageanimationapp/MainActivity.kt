package com.tops.kotlin.imageanimationapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.tops.kotlin.imageanimationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Rotate Button
        binding.btnRotate.setOnClickListener {
            rotateImage()
        }

        // Blink Button
        binding.btnBlink.setOnClickListener {
            blinkImage()
        }

        // Move Button
        binding.btnMove.setOnClickListener {
            MoveImage()
        }

        // Zoom Buttons
        binding.btnZoomIn.setOnClickListener {
            zoomInImage()
        }
        binding.btnZoomOut.setOnClickListener {
            zoomOutImage()
        }

        // Frame Animation Button
        binding.btnFrame.setOnClickListener {
            startFrameAnimation()
        }
    }

    private fun rotateImage() {
        val rotateAnimator = ObjectAnimator.ofFloat(binding.ivLogo, "rotation", 0f, 360f).apply {
            duration = 1000
        }
        rotateAnimator.start()
    }

    private fun blinkImage() {
        val blinkAnimation = AlphaAnimation(0.0f, 1.0f).apply {
            duration = 500
            startOffset = 20
        }
        binding.ivLogo.startAnimation(blinkAnimation)
    }

    private fun zoomInImage() {
        val scaleXAnimator = ObjectAnimator.ofFloat(binding.ivLogo, "scaleX", 1f, 1.5f).apply {
            duration = 1000
        }

        val scaleYAnimator = ObjectAnimator.ofFloat(binding.ivLogo, "scaleY", 1f, 1.5f).apply {
            duration = 1000
        }

        AnimatorSet().apply {
            playTogether(scaleXAnimator, scaleYAnimator)
            start()
        }
    }

    private fun zoomOutImage() {
        val scaleXAnimator = ObjectAnimator.ofFloat(binding.ivLogo, "scaleX", 1.5f, 1f).apply {
            duration = 1000
        }

        val scaleYAnimator = ObjectAnimator.ofFloat(binding.ivLogo, "scaleY", 1.5f, 1f).apply {
            duration = 1000
        }

        AnimatorSet().apply {
            playTogether(scaleXAnimator, scaleYAnimator)
            start()
        }
    }

    private fun MoveImage() {
        // Move Image (left to right)
        val moveAnimator = ValueAnimator.ofFloat(0f, 500f).apply {
            duration = 2000
            addUpdateListener { animation ->
                val value = animation.animatedValue as Float
                binding.ivCycling.translationX = value
            }
        }
        moveAnimator.start()
    }

    private fun startFrameAnimation() {
        binding.ivBg.setBackgroundResource(R.drawable.progress_animation)
        val frameAnimation = binding.ivBg.background as AnimationDrawable

        // Start the animation
        frameAnimation.start()

        // Stop the animation after 5 seconds with a fade-out effect
        binding.ivBg.postDelayed({
            val fadeOut = AlphaAnimation(1f, 0f).apply {
                duration = 500 // Smooth fade-out duration
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {}
                    override fun onAnimationEnd(animation: Animation?) {
                        frameAnimation.stop() // Stop animation after fade-out
                    }

                    override fun onAnimationRepeat(animation: Animation?) {}
                })
            }
            binding.ivBg.startAnimation(fadeOut)
        }, 5000)
    }

}