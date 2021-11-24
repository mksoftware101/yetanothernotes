package com.mksoftware101.notes.features.noteList.ui

import androidx.annotation.StringRes

sealed class NotesListError
object GetNotesListError : NotesListError()
data class RemoveNoteError(@StringRes val messageResId: Int) : NotesListError()