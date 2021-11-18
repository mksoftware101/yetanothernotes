package com.mksoftware101.notes.features.noteList.ui.extensions

import com.mksoftware101.notes.features.noteList.data.types.NoteList
import com.mksoftware101.notes.features.noteList.ui.NoteListItemViewModel

fun NoteList.toDisplay() : List<NoteListItemViewModel> {
    return map {
        NoteListItemViewModel(it.content.substring(0, 8), it.creationDate, it.id, letter = it.content.take(1))
    }
}