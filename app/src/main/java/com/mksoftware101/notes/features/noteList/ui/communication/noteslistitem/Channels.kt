package com.mksoftware101.notes.features.noteList.ui.communication.noteslistitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

sealed class BaseState
object AddToFavouriteFailed : BaseState()
object RemoveFromFavouriteFailed : BaseState()

class Channels {
    private val _error = MutableLiveData<BaseState>()
    val error: LiveData<BaseState> = _error

    private val _output = MutableLiveData<BaseState>()
    val output: LiveData<BaseState> = _output

    fun emitError(errorState: BaseState) {
        _error.value = errorState
    }

    fun emit(state: BaseState) {
        _output.value = state
    }
}