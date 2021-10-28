package com.mksoftware101.notes.home

import com.mksoftware101.notes.base.BaseViewModel
import com.mksoftware101.notes.home.HomeIntent.FetchAllNotes
import com.mksoftware101.notes.home.HomeState.AllNotesFetchedState
import com.mksoftware101.notes.home.domain.FetchAllNotesUseCase

class HomeViewModel : BaseViewModel<HomeIntent, HomeState>() {
    private val fetchAllNotesUseCase: FetchAllNotesUseCase = FetchAllNotesUseCase()

    override fun sendIntent(intent: HomeIntent) {
        when (intent) {
            is FetchAllNotes -> handleFetchAllNotesIntent()
        }
    }

    private fun handleFetchAllNotesIntent() {
        val notes = fetchAllNotesUseCase.run()
        submit(AllNotesFetchedState(notes))
    }
}