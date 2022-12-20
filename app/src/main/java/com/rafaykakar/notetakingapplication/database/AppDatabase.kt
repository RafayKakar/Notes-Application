package com.rafaykakar.stickynoteapplication.database

import androidx.room.*
import com.rafaykakar.notetakingapplication.database.DBDataConverter
import java.util.*


@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(DBDataConverter::class)
abstract class AppDatabase : RoomDatabase() {
      abstract fun notesDao():NotesDao
}

