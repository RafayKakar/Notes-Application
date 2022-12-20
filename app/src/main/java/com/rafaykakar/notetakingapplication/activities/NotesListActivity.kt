package com.rafaykakar.notetakingapplication.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaykakar.notetakingapplication.R
import com.rafaykakar.notetakingapplication.adapters.NotesAdapter
import com.rafaykakar.notetakingapplication.databinding.ActivityNotesListBinding
import com.rafaykakar.notetakingapplication.viewmodels.NoteListViewModel
import com.rafaykakar.stickynoteapplication.database.NoteEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class NotesListActivity : AppCompatActivity(), NotesAdapter.NoteSelection {

    lateinit var binding: ActivityNotesListBinding
    lateinit var context: Context
    val viewModel by viewModels<NoteListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notes_list)
        context = binding.root.context
        inits()
    }

    private fun inits() {

        GlobalScope.launch(Dispatchers.Main) {

            var notesList = arrayListOf<NoteEntity>()

            //Notes List Observer
            viewModel.getAllNotes().observe(this@NotesListActivity, Observer {

                //Update UI on notes list size
                if (it.size > 0)
                    binding.apply {
                        nonote.visibility = View.INVISIBLE
                        nonotetext.visibility = View.INVISIBLE
                        notesRecyclerview.visibility = View.VISIBLE
                        deleteAllNotes.visibility = View.VISIBLE
                    }
                else
                    binding.apply {
                        nonote.visibility = View.VISIBLE
                        nonotetext.visibility = View.VISIBLE
                        notesRecyclerview.visibility = View.INVISIBLE
                        deleteAllNotes.visibility = View.INVISIBLE
                    }

                //Inflate notes in recyclerview
                notesList.clear()
                notesList.addAll(it)
                binding.notesRecyclerview.apply {
                    layoutManager = LinearLayoutManager(this@NotesListActivity)
                    adapter =
                        NotesAdapter(this@NotesListActivity, notesList, this@NotesListActivity)
                }
            })

            //ItemTouchHelper to swipe item on right to delete
            ItemTouchHelper(object :
                ItemTouchHelper.SimpleCallback(
                    ItemTouchHelper.UP,
                    ItemTouchHelper.RIGHT
                ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    direction: Int
                ) {
                    GlobalScope.launch {
                        viewModel.deleteNote(notesList.get(viewHolder.adapterPosition))
                    }
                }
            }).attachToRecyclerView(binding.notesRecyclerview)
        }

        binding.apply {

            //Add new note
            addNoteBtn.setOnClickListener {
                startActivity(Intent(this@NotesListActivity, NoteEditorActivity::class.java))
            }

            //Clear all notes
            deleteAllNotes.setOnClickListener {
               GlobalScope.launch {
                   viewModel.deleteAllNotes()
               }
            }
        }
    }


    //Note selected callback
    override fun note(noteEntity: NoteEntity){

        //Open NoteEditorActivity
        startActivity(Intent(
            this,
            NoteEditorActivity::class.java).apply {
            GlobalScope.launch {
                putExtra("NOTE_OBJECT", noteEntity)
            }
        })
    }
}