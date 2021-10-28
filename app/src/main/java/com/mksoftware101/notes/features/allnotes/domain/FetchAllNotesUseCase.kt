package com.mksoftware101.notes.features.allnotes.domain

import com.mksoftware101.notes.core.Notes
import com.mksoftware101.notes.features.allnotes.model.Note

class FetchAllNotesUseCase {
    fun run(): Notes {
        return listOf(Note("Notes One"), Note("Notes Two"), Note("Notes three"))
    }
}