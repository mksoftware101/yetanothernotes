package com.mksoftware101.notes.features.notes.ui

import androidx.lifecycle.ViewModel
import timber.log.Timber

class ItemNotesViewModel(var title: String, var creationDate: String) : ViewModel() {
    fun onClick() {
        Timber.d("[d] Clicked $title")
    }
}