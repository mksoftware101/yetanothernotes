package com.mksoftware101.notes.legacy.features.noteList.domain.extensions

import com.mksoftware101.notes.legacy.core.db.NotesEntity
import com.mksoftware101.notes.legacy.features.noteList.data.Note
import com.mksoftware101.notes.legacy.features.noteList.data.types.NoteList
import com.mksoftware101.notes.legacy.features.noteList.ui.NoteListItemViewModel

internal fun List<NotesEntity>.toNoteList(): NoteList {
    return this.map { it.toNote() }
}

internal fun NotesEntity.toNote() = Note(
    id = this.id,
    creationDate = this.creationDate,
    isFavourite = this.favourite,
    content = this.data
)

internal fun Note.toNotesEntity() = NotesEntity(
    id = this.id,
    creationDate = this.creationDate,
    favourite = this.isFavourite,
    data = this.content
)

internal fun List<NoteListItemViewModel>.isIndexOutOfRange(index: Int): Boolean {
    return index < 0 || index >= this.size
}

@JvmName("isIndexOutOfRangeNoteList")
internal fun List<Note>.isIndexOutOfRange(index: Int): Boolean {
    return index < 0 || index >= this.size
}
