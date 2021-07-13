package com.mksoftware101.notes.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<I : BaseIntent, S : BaseState> : ViewModel() {
    private val mutableState: MutableLiveData<S> = MutableLiveData()

    abstract fun sendIntent(intent: I)

    fun getState(): LiveData<S> = mutableState

    fun submit(state: S) {
        mutableState.value = state
    }
}