package com.tops.kotlin.stickynotesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.stickynotesapp.databinding.ActivityAddNoteBinding
import com.tops.kotlin.stickynotesapp.dbhelpers.NotesDBHelper
import com.tops.kotlin.stickynotesapp.models.Note

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    private lateinit var db: NotesDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDBHelper(this)

        binding.btnSave.setOnClickListener {
            val title = binding.edtTitle.text.toString().trim()
            val content = binding.edtContent.text.toString().trim()

            val note = Note(0, title, content)
            db.insertNote(note)
            finish()

            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
        }
    }
}