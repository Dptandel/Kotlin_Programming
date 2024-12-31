package com.tops.kotlin.rtodrivinglicensetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.appbar.MaterialToolbar
import com.tops.kotlin.rtodrivinglicensetest.databinding.ActivityMenuBinding
import com.tops.kotlin.rtodrivinglicensetest.fragments.FaqFragment
import com.tops.kotlin.rtodrivinglicensetest.fragments.FormsFragment
import com.tops.kotlin.rtodrivinglicensetest.fragments.LearnFragment
import com.tops.kotlin.rtodrivinglicensetest.fragments.MarkForRevisionFragment
import com.tops.kotlin.rtodrivinglicensetest.fragments.MockTestFragment
import com.tops.kotlin.rtodrivinglicensetest.fragments.PracticeFragment
import com.tops.kotlin.rtodrivinglicensetest.fragments.ProcessFragment
import com.tops.kotlin.rtodrivinglicensetest.fragments.RtoOfficeFragment
import com.tops.kotlin.rtodrivinglicensetest.fragments.StatisticsFragment

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: MaterialToolbar = binding.toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)

        val title = intent.getStringExtra("ITEM_TITLE") ?: "RTO Driving License Test"
        setTitle(title)

        val fragment =
            when (title) {
                "Learn" -> LearnFragment()
                "Practice" -> PracticeFragment()
                "Mock Test" -> MockTestFragment()
                "Marked For Revision" -> MarkForRevisionFragment()
                "RTO Office" -> RtoOfficeFragment()
                "Process" -> ProcessFragment()
                "Statistics" -> StatisticsFragment()
                "Forms" -> FormsFragment()
                "FAQ" -> FaqFragment()
                else -> return
            }
        supportFragmentManager.beginTransaction().replace(R.id.fragment_view, fragment).commit()
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}