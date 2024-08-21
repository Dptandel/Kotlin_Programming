package com.tops.kotlin.fragmentlifecycleapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tops.kotlin.fragmentlifecycleapp.R

class SecondFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("FRAGMENT-2", "onCreate: method called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FRAGMENT-2", "onCreateView: method called")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("FRAGMENT-2", "onViewCreated: method called")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        Log.d("FRAGMENT-2", "onViewStateRestored: method called")
    }

    override fun onStart() {
        super.onStart()

        Log.d("FRAGMENT-2", "onStart: method called")

    }

    override fun onResume() {
        super.onResume()

        Log.d("FRAGMENT-2", "onResume: method called")
    }

    override fun onPause() {
        super.onPause()

        Log.d("FRAGMENT-2", "onPause: method called")
    }

    override fun onStop() {
        super.onStop()

        Log.d("FRAGMENT-2", "onStop: method called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.d("FRAGMENT-2", "onSaveInstanceState: method called")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.d("FRAGMENT-2", "onDestroyView: method called")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("FRAGMENT-2", "onDestroy: method called")

    }
}