package com.mksoftware101.notes.features.noteList.domain.extensions

import com.mksoftware101.notes.core.db.NotesEntity
import com.mksoftware101.notes.features.noteList.data.Note
import com.mksoftware101.notes.features.noteList.data.types.NoteList

internal fun List<NotesEntity>.toNoteList(): NoteList {
    return this.map { it.toNote() }
}

internal fun NotesEntity.toNote() = Note(
    id = this.id.toInt(),
    creationDate = this.creationDate,
    content = this.data
)