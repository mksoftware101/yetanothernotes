package com.mksoftware101.notes.features.noteList.domain.di

import com.mksoftware101.notes.core.repository.NotesRepository
import com.mksoftware101.notes.features.noteList.domain.GetObservableNoteListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object NoteListDomainModule {

    @Provides
    fun prividesGetObservableNoteListUseCase(repository: NotesRepository) = GetObservableNoteListUseCase(repository)
}