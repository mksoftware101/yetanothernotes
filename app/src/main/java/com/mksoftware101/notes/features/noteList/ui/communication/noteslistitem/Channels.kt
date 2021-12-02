package com.mksoftware101.notes.features.noteList.ui.communication.noteslistitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

sealed class BaseState
object AddToFavouriteFailed : BaseState()
object RemoveFromFavouriteFailed : BaseState()

class ErrorChannel {
    private val _error = MutableLiveData<BaseState>()
    val error: LiveData<BaseState> = _error

    fun submit(errorState: BaseState) {
        _error.value = errorState
    }
}