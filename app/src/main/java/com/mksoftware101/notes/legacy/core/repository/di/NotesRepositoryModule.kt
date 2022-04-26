package com.mksoftware101.notes.legacy.core.repository.di

import com.mksoftware101.notes.legacy.core.db.NotesDb
import com.mksoftware101.notes.legacy.core.repository.NotesRepository
import com.mksoftware101.notes.legacy.core.repository.NotesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object NotesRepositoryModule {

    @Provides
    fun providesNotesRepository(db: NotesDb): NotesRepository = NotesRepositoryImpl(db)
}