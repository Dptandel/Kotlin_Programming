package com.tops.kotlin.rtodrivinglicensetest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.kotlin.rtodrivinglicensetest.databinding.ItemOptionBinding

class OptionsAdapter(private val options: List<String?>) :
    RecyclerView.Adapter<OptionsAdapter.OptionViewHolder>() {
    inner class OptionViewHolder(val binding: ItemOptionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OptionViewHolder {
        val binding = ItemOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OptionViewHolder(binding)
    }

    override fun getItemCount(): Int = options.size

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.binding.tvOption.text = options[position]
    }
}