package com.mksoftware101.notes.legacy.core.db.di

import android.content.Context
import androidx.room.Room
import com.mksoftware101.notes.legacy.core.db.CreateCallback
import com.mksoftware101.notes.legacy.core.db.NotesDao
import com.mksoftware101.notes.legacy.core.db.NotesDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotesDbModule {

    @Provides
    @Named("databaseName")
    fun providesDatabaseName() = "notesDb"

    @Provides
    @Singleton
    fun providesNotesDb(
        @ApplicationContext appContext: Context,
        @Named("databaseName") name: String,
        createCallback: CreateCallback
    ): NotesDb {
        return Room.databaseBuilder(appContext, NotesDb::class.java, name)
            .addCallback(createCallback)
            .build()
    }

    @Provides
    fun providesNotesDao(db: NotesDb) : NotesDao = db.getNotesDao()
}