package com.rafaykakar.stickynoteapplication.modules

import android.content.Context
import androidx.room.Room
import com.rafaykakar.stickynoteapplication.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    private val DATABASE_NAME = "notesdatabase.db"

    @Provides
    @Singleton
    fun notesDao(db: AppDatabase) = db.notesDao()

    @Provides
    @Singleton
    fun notesDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }
}