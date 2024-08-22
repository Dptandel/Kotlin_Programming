package com.tops.kotlin.fragmenttofragmentdataapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.tops.kotlin.fragmenttofragmentdataapp.R


class MainFragment : Fragment() {

    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var mobile: EditText
    private lateinit var email: EditText

    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try{
            if(context is Communicator){
                communicator = context
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val submitBtn = view.findViewById<AppCompatButton>(R.id.submitBtn)

        firstName = view.findViewById(R.id.firstName)
        lastName = view.findViewById(R.id.lastName)
        mobile = view.findViewById(R.id.mobile)
        email = view.findViewById(R.id.email)

        submitBtn.setOnClickListener {

            val fname = firstName.text.toString().trim()
            val lname = lastName.text.toString().trim()
            val mobile = mobile.text.toString().trim()
            val email = email.text.toString().trim()

            if(fname.isEmpty() || lname.isEmpty() || mobile.isEmpty() || email.isEmpty()){
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
            communicator.sendDetails( fname, lname, mobile, email )
            }
        }
    }
}

interface Communicator {
    fun sendDetails(firstName: String, lastName: String, mobile: String, email: String)
}