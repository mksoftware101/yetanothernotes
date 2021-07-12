package com.mksoftware101.notes.home

import com.mksoftware101.notes.base.Notes

sealed class HomeState
data class AllNotesFetchedState(val data: Notes) : HomeState()