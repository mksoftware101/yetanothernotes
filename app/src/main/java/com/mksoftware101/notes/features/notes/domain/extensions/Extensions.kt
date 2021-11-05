package com.mksoftware101.notes.features.notes.domain.extensions

import com.mksoftware101.notes.core.db.NotesEntity
import com.mksoftware101.notes.features.notes.model.Note
import com.mksoftware101.notes.features.notes.types.Notes

internal fun List<NotesEntity>.toNotes(): Notes {
    return this.map { it.toNote() }
}

internal fun NotesEntity.toNote() = Note(
    id = this.id.toInt(),
    creationDate = this.creationDate,
    content = this.data
)