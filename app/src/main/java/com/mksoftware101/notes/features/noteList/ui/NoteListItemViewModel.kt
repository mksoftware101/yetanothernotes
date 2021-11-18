package com.mksoftware101.notes.features.noteList.ui

import androidx.annotation.ColorRes
import androidx.lifecycle.ViewModel
import com.mksoftware101.notes.R
import timber.log.Timber

class NoteListItemViewModel(var title: String, var creationDate: String, var id: Int, @ColorRes val color: Int = R.color.secondary, val letter: String='A'.toString()) : ViewModel() {
    fun onClick() {
        Timber.d("[d] Clicked $title")
    }
}