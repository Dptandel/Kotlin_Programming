package com.tops.kotlin.rtodrivinglicensetest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tops.kotlin.rtodrivinglicensetest.models.MenuItem
import com.tops.kotlin.rtodrivinglicensetest.databinding.MenuItemBinding

class MenuItemAdapter(
    private val context: Context,
    private val menuItems: List<MenuItem>,
    private val onItemClick: OnItemClickListener
) :
    RecyclerView.Adapter<MenuItemAdapter.MenuViewHolder>() {

    fun interface OnItemClickListener {
        fun onItemClick(menuItem: MenuItem)
    }

    inner class MenuViewHolder(var binding: MenuItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int = menuItems.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menuItems[position]
        holder.binding.ivMenuIcon.setImageResource(menu.iconResId)
        holder.binding.tvMenuItem.text = menu.title
        holder.itemView.setOnClickListener {
            onItemClick.onItemClick(menu)
        }
    }
}