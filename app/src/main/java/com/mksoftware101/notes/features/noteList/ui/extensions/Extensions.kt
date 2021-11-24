package com.mksoftware101.notes.features.noteList.ui.extensions

import com.mksoftware101.notes.features.noteList.data.types.NoteList
import com.mksoftware101.notes.features.noteList.ui.NoteListItemViewModel

fun NoteList.toItemsViewModel() : List<NoteListItemViewModel> {
    return map {
        NoteListItemViewModel(it.content.substring(0, 20), it.creationDate, it.id, letter = it.content.take(1))
    }
}