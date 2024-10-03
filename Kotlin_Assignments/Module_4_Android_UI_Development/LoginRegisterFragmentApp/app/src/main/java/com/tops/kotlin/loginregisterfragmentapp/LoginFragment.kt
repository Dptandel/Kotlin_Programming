package com.tops.kotlin.loginregisterfragmentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tops.kotlin.loginregisterfragmentapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.txtCreateAccount.setOnClickListener {
            val registerFragment = RegisterFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, registerFragment)
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }
}