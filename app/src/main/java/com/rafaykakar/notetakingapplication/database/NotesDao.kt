package com.rafaykakar.stickynoteapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteEntity: NoteEntity?)


    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Int): NoteEntity?


    @Query("SELECT * FROM notes ORDER BY date DESC")
    fun getAllNotes(): LiveData<List<NoteEntity>>


    @Delete
    fun deleteNote(noteEntity: NoteEntity?)


    @Query("DELETE FROM notes")
    fun deleteAllNotes(): Int


    @Query("SELECT COUNT(*) from notes")
    fun getCount(): LiveData<Int>
}