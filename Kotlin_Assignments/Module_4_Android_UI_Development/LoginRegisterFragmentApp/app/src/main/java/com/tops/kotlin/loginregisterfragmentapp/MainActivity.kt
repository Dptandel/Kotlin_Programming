package com.tops.kotlin.loginregisterfragmentapp

import android.os.Bundle
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.loginregisterfragmentapp.databinding.ActivityMainBinding
import com.tops.kotlin.loginregisterfragmentapp.databinding.FragmentLoginBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val loginFragment = LoginFragment()
        fragmentTransaction.add(R.id.fragmentContainer, loginFragment)
        fragmentTransaction.commit()
    }
}