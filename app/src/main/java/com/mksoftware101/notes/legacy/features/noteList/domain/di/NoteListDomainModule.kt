package com.mksoftware101.notes.legacy.features.noteList.domain.di

import com.mksoftware101.notes.legacy.core.repository.NotesRepository
import com.mksoftware101.notes.legacy.features.noteList.domain.GetObservableNotesListUseCase
import com.mksoftware101.notes.legacy.features.noteList.domain.RemoveNoteUseCase
import com.mksoftware101.notes.legacy.features.noteList.domain.UpdateNoteUseCase
import com.mksoftware101.notes.legacy.features.noteList.ui.NotesListItemFactory
import com.mksoftware101.notes.legacy.features.noteList.ui.communication.noteslistitem.Channels
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object NoteListDomainModule {

    @Provides
    fun prividesGetObservableNoteListUseCase(repository: NotesRepository) =
        GetObservableNotesListUseCase(repository)

    @Provides
    fun providesRemoveNoteUseCase(repository: NotesRepository) = RemoveNoteUseCase(repository)

    @Provides
    fun providesUpdateNoteUseCase(repository: NotesRepository) = UpdateNoteUseCase(repository)

    @Provides
    fun providesNotesListItemFactory(updateNoteUseCase: UpdateNoteUseCase, channel: Channels) =
        NotesListItemFactory(updateNoteUseCase, channel)
}