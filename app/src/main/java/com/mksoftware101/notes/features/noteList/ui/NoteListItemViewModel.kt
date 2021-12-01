package com.mksoftware101.notes.features.noteList.ui

import androidx.annotation.ColorRes
import androidx.lifecycle.ViewModel
import com.mksoftware101.notes.R
import com.mksoftware101.notes.features.noteList.domain.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import timber.log.Timber

class NoteListItemViewModel(var title: String, var creationDate: String, var id: Int, @ColorRes val color: Int = R.color.secondary, val letter: String) : ViewModel() {

    private var updateNoteUseCase: UpdateNoteUseCase? = null

    fun addUpdateUseCase(useCase: UpdateNoteUseCase) : NoteListItemViewModel{
        updateNoteUseCase = useCase
        return this
    }

    fun onClick() {
        Timber.d("[d] Clicked $title")
    }

    fun onFavouriteChange() {
        Timber.d("[d] onFavourite clicked $updateNoteUseCase")
    }
}