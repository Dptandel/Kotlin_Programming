package com.tops.kotlin.stickynotesapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.kotlin.stickynotesapp.adapters.NotesAdapter
import com.tops.kotlin.stickynotesapp.databinding.ActivityMainBinding
import com.tops.kotlin.stickynotesapp.dbhelpers.NotesDBHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var db : NotesDBHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDBHelper(this)
        notesAdapter = NotesAdapter(db.getAllNotes(), this)

        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = notesAdapter

        binding.fabAddButton.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshNotes(db.getAllNotes())
    }
}