package com.mksoftware101.notes.features.allnotes.domain

import com.mksoftware101.notes.core.repository.NotesRepository
import com.mksoftware101.notes.core.usecase.NoParamUseCase
import com.mksoftware101.notes.features.allnotes.types.Notes

class FetchNotesUseCase(private val repository: NotesRepository) : NoParamUseCase<Notes> {
    override fun run(): Notes {
        return emptyList()
    }
}