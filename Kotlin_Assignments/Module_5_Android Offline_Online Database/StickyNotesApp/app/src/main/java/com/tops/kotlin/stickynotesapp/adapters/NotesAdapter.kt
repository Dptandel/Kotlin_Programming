package com.tops.kotlin.stickynotesapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.tops.kotlin.stickynotesapp.UpdateNoteActivity
import com.tops.kotlin.stickynotesapp.databinding.NoteItemBinding
import com.tops.kotlin.stickynotesapp.dbhelpers.NotesDBHelper
import com.tops.kotlin.stickynotesapp.models.Note

class NotesAdapter(private var notes: List<Note>, context: Context) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val db: NotesDBHelper = NotesDBHelper(context)

    class NoteViewHolder(var binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.tvTitle.text = note.title
        holder.binding.tvContent.text = note.content

        holder.binding.ivEdit.setOnClickListener {
            it.context.startActivity(Intent(it.context, UpdateNoteActivity::class.java).apply {
                putExtra("note_id", note.id)
            })
        }

        holder.binding.ivDelete.setOnClickListener {
            val dialog = AlertDialog.Builder(it.context)
            dialog.setTitle("Delete Note")
            dialog.setMessage("Are you sure you want to delete this note?")
            dialog.setPositiveButton("Yes") { _, _ ->
                db.deleteNote(note.id)
                refreshNotes(db.getAllNotes())
                Toast.makeText(it.context, "Note deleted", Toast.LENGTH_SHORT).show()
            }
            dialog.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    fun refreshNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }

}