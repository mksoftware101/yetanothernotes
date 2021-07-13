package com.mksoftware101.notes.home

import com.mksoftware101.notes.base.BaseIntent

sealed class HomeIntent : BaseIntent {
    object FetchAllNotes : HomeIntent()
}