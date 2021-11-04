package com.mksoftware101.notes.core.db.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mksoftware101.notes.core.db.CreateCallback
import com.mksoftware101.notes.core.db.NotesDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NotesDbModule {

    @Provides
    @Named("databaseName")
    fun providesDatabaseName() = "notesDb"

    @Provides
    fun providesCreateCallabck(): RoomDatabase.Callback = CreateCallback()

    @Provides
    fun providesNotesDb(
        @ApplicationContext appContext: Context,
        @Named("databaseName") name: String,
        createCallback: RoomDatabase.Callback
    ): NotesDb =
        Room.databaseBuilder(appContext, NotesDb::class.java, name)
            .addCallback(createCallback)
            .allowMainThreadQueries() // ToDo Only in early bird stage, remove it afterwards
            .build()
}