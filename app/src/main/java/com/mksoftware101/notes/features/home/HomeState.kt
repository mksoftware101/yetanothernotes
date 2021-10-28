package com.mksoftware101.notes.features.home

import com.mksoftware101.notes.core.BaseState
import com.mksoftware101.notes.core.Notes

sealed class HomeState : BaseState {
    object LoadingState : HomeState()
    data class AllNotesFetchedState(val data: Notes) : HomeState()
    data class ErrorState(val data: String) : HomeState()
}