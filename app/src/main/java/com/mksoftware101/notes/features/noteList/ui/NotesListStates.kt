package com.mksoftware101.notes.features.noteList.ui

import androidx.annotation.StringRes

sealed class NotesListErrorState
object GetNotesListError : NotesListErrorState()
data class RemoveNoteError(@StringRes val messageResId: Int) : NotesListErrorState()

sealed class NotesListSuccessState
object RemoveNoteSuccessState : NotesListSuccessState()