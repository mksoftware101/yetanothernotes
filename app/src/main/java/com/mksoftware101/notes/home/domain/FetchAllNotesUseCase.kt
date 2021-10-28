package com.mksoftware101.notes.home.domain

import com.mksoftware101.notes.base.Notes
import com.mksoftware101.notes.home.model.Note

class FetchAllNotesUseCase {
    fun run(): Notes {
        return listOf(Note("Notes One"), Note("Notes Two"), Note("Notes three"))
    }
}