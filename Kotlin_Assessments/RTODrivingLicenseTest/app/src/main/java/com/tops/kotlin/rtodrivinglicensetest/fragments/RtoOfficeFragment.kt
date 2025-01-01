package com.tops.kotlin.rtodrivinglicensetest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.tops.kotlin.rtodrivinglicensetest.R
import com.tops.kotlin.rtodrivinglicensetest.databinding.FragmentRtoOfficeBinding

class RtoOfficeFragment : Fragment() {

    private lateinit var binding: FragmentRtoOfficeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRtoOfficeBinding.inflate(inflater, container, false)

        val states = listOf("Ahmedabad", "Surat", "Navsari", "Valsad", "Vadodara")
        val stateAdapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, states)
        binding.acTvState.setAdapter(stateAdapter)

        binding.acTvState.setOnItemClickListener { parent, view, position, id ->
            val selectedState = parent.getItemAtPosition(position).toString()
            binding.tilRtoOffice.helperText = "You selected: $selectedState"
        }

        return binding.root
    }
}