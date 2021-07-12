package com.mksoftware101.notes.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mksoftware101.notes.home.domain.FetchAllNotesUseCase

class HomeViewModel : ViewModel() {

    private val fetchAllNotesUseCase: FetchAllNotesUseCase = FetchAllNotesUseCase()

    private val mutableState: MutableLiveData<HomeState> = MutableLiveData()
    val state: LiveData<HomeState> = mutableState

    fun fetchAllNotes() {
        val notes = fetchAllNotesUseCase.run()
        mutableState.value = AllNotesFetchedState(notes)
    }
}