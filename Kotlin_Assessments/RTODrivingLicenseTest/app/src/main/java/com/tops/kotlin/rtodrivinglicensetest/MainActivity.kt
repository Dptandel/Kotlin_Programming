package com.tops.kotlin.rtodrivinglicensetest

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.tops.kotlin.rtodrivinglicensetest.adapters.MenuItemAdapter
import com.tops.kotlin.rtodrivinglicensetest.databinding.ActivityMainBinding
import com.tops.kotlin.rtodrivinglicensetest.models.MenuItem

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val toolbar: MaterialToolbar = binding.toolbar
        setSupportActionBar(toolbar)

        // Prepare Data
        val menuItems = listOf(
            MenuItem(R.drawable.ic_learn, "Learn"),
            MenuItem(R.drawable.ic_practice, "Practice"),
            MenuItem(R.drawable.ic_mock_test, "Mock Test"),
            MenuItem(R.drawable.ic_mark_for_revision, "Marked For Revision"),
            MenuItem(R.drawable.ic_rto_office, "RTO Office"),
            MenuItem(R.drawable.ic_process, "Process"),
            MenuItem(R.drawable.ic_statistics, "Statistics"),
            MenuItem(R.drawable.ic_forms, "Forms"),
            MenuItem(R.drawable.ic_faq, "FAQ")
        )

        binding.rvLicenseMenus.layoutManager =
            GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)

        binding.rvLicenseMenus.adapter = MenuItemAdapter(
            this, menuItems, onItemClick = { menuItem ->
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("ITEM_TITLE", menuItem.title)
                startActivity(intent)
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                true
            }

            R.id.action_settings -> {
                true
            }

            R.id.action_about -> {
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}