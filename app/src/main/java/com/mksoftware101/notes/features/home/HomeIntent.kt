package com.mksoftware101.notes.features.home

import com.mksoftware101.notes.core.BaseIntent

sealed class HomeIntent : BaseIntent {
    object FetchAllNotes : HomeIntent()
}