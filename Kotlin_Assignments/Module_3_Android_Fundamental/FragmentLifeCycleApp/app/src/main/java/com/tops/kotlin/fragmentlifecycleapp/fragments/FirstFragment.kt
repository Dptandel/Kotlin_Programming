package com.tops.kotlin.fragmentlifecycleapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.tops.kotlin.fragmentlifecycleapp.R

class FirstFragment : Fragment() {

    private lateinit var btnSecond: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("FRAGMENT-1", "onCreate: method called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FRAGMENT-1", "onCreateView: method called")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSecond = view.findViewById(R.id.btnSecond)

        btnSecond.setOnClickListener {
            val manager = requireActivity().supportFragmentManager
            val transition = manager.beginTransaction()
            transition.replace(R.id.fragmentContainer, SecondFragment())
            transition.addToBackStack(null)
            transition.commit()
        }

        Log.d("FRAGMENT-1", "onViewCreated: method called")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        Log.d("FRAGMENT-1", "onViewStateRestored: method called")
    }

    override fun onStart() {
        super.onStart()

        Log.d("FRAGMENT-1", "onStart: method called")

    }

    override fun onResume() {
        super.onResume()

        Log.d("FRAGMENT-1", "onResume: method called")
    }

    override fun onPause() {
        super.onPause()

        Log.d("FRAGMENT-1", "onPause: method called")
    }

    override fun onStop() {
        super.onStop()

        Log.d("FRAGMENT-1", "onStop: method called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.d("FRAGMENT-1", "onSaveInstanceState: method called")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.d("FRAGMENT-1", "onDestroyView: method called")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("FRAGMENT-1", "onDestroy: method called")

    }
}