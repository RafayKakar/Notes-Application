package com.rafaykakar.stickynoteapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "notes")
data class NoteEntity(
   var heading:String, var text: String, var date: Date
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}