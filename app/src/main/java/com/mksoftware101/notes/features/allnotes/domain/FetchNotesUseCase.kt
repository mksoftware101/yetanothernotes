package com.mksoftware101.notes.features.allnotes.domain

import com.mksoftware101.notes.core.repository.NotesRepository
import com.mksoftware101.notes.core.usecase.NoParamUseCase
import com.mksoftware101.notes.features.allnotes.domain.extensions.toNotes
import com.mksoftware101.notes.features.allnotes.types.Notes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchNotesUseCase(private val repository: NotesRepository) : NoParamUseCase<Flow<Notes>> {
    override fun run(): Flow<Notes> {
        return repository.getAllNotes().map { it.toNotes() }
    }
}