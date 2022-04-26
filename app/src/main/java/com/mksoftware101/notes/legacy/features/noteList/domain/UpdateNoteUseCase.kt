package com.mksoftware101.notes.legacy.features.noteList.domain

import com.mksoftware101.notes.legacy.core.repository.NotesRepository
import com.mksoftware101.notes.legacy.core.usecase.SuspendUseCase
import com.mksoftware101.notes.legacy.features.noteList.data.Note
import com.mksoftware101.notes.legacy.features.noteList.domain.extensions.toNotesEntity

class UpdateNoteUseCase(private val repository: NotesRepository) : SuspendUseCase<Note, Unit> {
    override suspend fun run(value: Note) {
        repository.update(value.toNotesEntity())
    }
}