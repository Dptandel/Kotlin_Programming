package com.tops.kotlin.fragmenttofragmentdataapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tops.kotlin.fragmenttofragmentdataapp.R

class DetailsFragment : Fragment() {

    private lateinit var txtName: TextView
    private lateinit var txtMobile: TextView
    private lateinit var txtEmail: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtName = view.findViewById(R.id.txtName)
        txtMobile = view.findViewById(R.id.txtMobile)
        txtEmail = view.findViewById(R.id.txtEmail)
    }

    fun displayDetails(firstName: String, lastName: String, mobile: String, email: String) {
        txtName.text = "Name : $firstName $lastName"
        txtMobile.text = "Mobile : $mobile"
        txtEmail.text = "Email : $email"
    }

}