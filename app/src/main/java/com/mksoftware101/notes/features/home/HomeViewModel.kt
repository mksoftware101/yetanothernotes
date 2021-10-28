package com.mksoftware101.notes.features.home

import com.mksoftware101.notes.core.BaseViewModel
import com.mksoftware101.notes.features.home.HomeIntent.FetchAllNotes
import com.mksoftware101.notes.features.home.HomeState.AllNotesFetchedState
import com.mksoftware101.notes.features.home.domain.FetchAllNotesUseCase

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