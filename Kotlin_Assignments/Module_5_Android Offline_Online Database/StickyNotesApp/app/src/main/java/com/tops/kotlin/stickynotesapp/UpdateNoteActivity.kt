package com.tops.kotlin.stickynotesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.stickynotesapp.databinding.ActivityUpdateNoteBinding
import com.tops.kotlin.stickynotesapp.dbhelpers.NotesDBHelper
import com.tops.kotlin.stickynotesapp.models.Note

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NotesDBHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDBHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1) {
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        binding.edtUpdateTitle.setText(note.title)
        binding.edtUpdateContent.setText(note.content)

        binding.btnUpdate.setOnClickListener {
            val newTitle = binding.edtUpdateTitle.text.toString()
            val newContent = binding.edtUpdateContent.text.toString()
            val updatedNote = Note(noteId, newTitle, newContent)
            db.updateNote(updatedNote)
            finish()

            Toast.makeText(this, "Note Changes Saved", Toast.LENGTH_SHORT).show()
        }
    }
}