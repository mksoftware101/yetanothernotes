package com.mksoftware101.notes.home

import com.mksoftware101.notes.base.BaseState
import com.mksoftware101.notes.base.Notes

sealed class HomeState : BaseState {
    object LoadingState : HomeState()
    data class AllNotesFetchedState(val data: Notes) : HomeState()
    data class ErrorState(val data: String) : HomeState()
}