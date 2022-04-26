package com.mksoftware101.notes.legacy.features.noteList.ui

import androidx.annotation.StringRes

sealed class NotesListErrorState
object IdleNoteErrorState : NotesListErrorState()
object GetNotesListError : NotesListErrorState()
data class RemoveNoteError(@StringRes val messageResId: Int) : NotesListErrorState()

sealed class NotesListSuccessState
object IdleNoteSuccessState : NotesListSuccessState()
object RemoveNoteSuccessState : NotesListSuccessState()