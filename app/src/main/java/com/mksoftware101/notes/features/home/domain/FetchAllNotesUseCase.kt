package com.mksoftware101.notes.features.home.domain

import com.mksoftware101.notes.core.Notes
import com.mksoftware101.notes.features.home.model.Note

class FetchAllNotesUseCase {
    fun run(): Notes {
        return listOf(Note("Notes One"), Note("Notes Two"), Note("Notes three"))
    }
}