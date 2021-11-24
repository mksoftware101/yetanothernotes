package com.mksoftware101.notes.features.noteList.domain

import com.mksoftware101.notes.core.repository.NotesRepository
import com.mksoftware101.notes.core.usecase.SuspendUseCase
import com.mksoftware101.notes.features.noteList.data.Note
import com.mksoftware101.notes.features.noteList.domain.extensions.toNotesEntity

class RemoveNoteUseCase(private val repository: NotesRepository) : SuspendUseCase<Note, Unit> {
    override suspend fun run(value: Note) {
        repository.remove(value.toNotesEntity())
    }
}