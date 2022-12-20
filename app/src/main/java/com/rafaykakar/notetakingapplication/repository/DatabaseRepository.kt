package com.rafaykakar.stickynoteapplication.repository

import androidx.lifecycle.LiveData
import com.rafaykakar.stickynoteapplication.database.NoteEntity
import com.rafaykakar.stickynoteapplication.database.NotesDao
import javax.inject.Inject
import javax.inject.Singleton


class DatabaseRepository @Inject constructor(
    private val notesDao: NotesDao
){
    suspend fun allNotes() = notesDao.getAllNotes()

    suspend fun addNote(noteEntity: NoteEntity) = notesDao.insertNote(noteEntity)

    suspend fun getNoteById(id:Int) = notesDao.getNoteById(id)

    suspend fun deleteNote(noteEntity: NoteEntity) = notesDao.deleteNote(noteEntity)

    suspend fun deleteAllNotes():Int = notesDao.deleteAllNotes()

    suspend fun getNotesCount(): LiveData<Int> = notesDao.getCount()
}