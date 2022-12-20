package com.rafaykakar.notetakingapplication.database

import androidx.room.TypeConverter
import java.util.*

class DBDataConverter {

    @TypeConverter
    fun dateConverter(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun timestampConverter(date: Date?): Long? {
        return date?.time
    }

}