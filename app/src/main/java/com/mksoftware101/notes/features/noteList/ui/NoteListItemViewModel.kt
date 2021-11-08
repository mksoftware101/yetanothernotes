package com.mksoftware101.notes.features.noteList.ui

import androidx.lifecycle.ViewModel
import timber.log.Timber

class NoteListItemViewModel(var title: String, var creationDate: String, var id: Int) : ViewModel() {
    fun onClick() {
        Timber.d("[d] Clicked $title")
    }
}