package com.mksoftware101.notes.legacy.features.noteList.data.types

import com.mksoftware101.notes.legacy.features.noteList.data.Note
import kotlinx.coroutines.flow.Flow

typealias NoteList = List<Note>
typealias ObservableNoteList = Flow<NoteList>