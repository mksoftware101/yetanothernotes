package com.mksoftware101.notes.core.repository

import com.mksoftware101.notes.core.db.NotesEntity

interface NotesRepository {
    fun getAllNotes(): List<NotesEntity>
}