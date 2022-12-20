package com.rafaykakar.notetakingapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rafaykakar.stickynoteapplication.database.NoteEntity
import com.rafaykakar.stickynoteapplication.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(private val dbrepository: DatabaseRepository) :
    ViewModel()  {

    suspend fun notesCount(): LiveData<Int> {
        return dbrepository.getNotesCount()
    }

    suspend fun getAllNotes(): LiveData<List<NoteEntity>> {
        return dbrepository.allNotes()
    }

    suspend fun getNoteById(id:Int): NoteEntity {
        return dbrepository.getNoteById(id)!!
    }

    suspend fun deleteNote(noteEntity: NoteEntity) {
        return dbrepository.deleteNote(noteEntity)
    }

    suspend fun deleteAllNotes():Int {
        return dbrepository.deleteAllNotes()
    }
}