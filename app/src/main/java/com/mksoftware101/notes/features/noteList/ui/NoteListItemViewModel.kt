package com.mksoftware101.notes.features.noteList.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mksoftware101.notes.features.noteList.data.Note
import com.mksoftware101.notes.features.noteList.domain.UpdateNoteUseCase
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class NoteListItemViewModel(
    private val note: Note,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {

    val title: String
        get() = note.content

    val letter: String
        get() = note.content.take(1)

    val creationDate: String
        get() = note.creationDate

    val isFavourite: Boolean
        get() = note.isFavourite

    val id: Long
        get() = note.id

    fun onClick() {
        // ToDo Navigate to details screen
    }

    fun onFavouriteChange() {
        viewModelScope.launch {
            try {
                updateNoteUseCase.run(
                    note.copy(isFavourite = !note.isFavourite)
                )
            } catch (e: Exception) {
                e.printStackTrace()
                // ToDo Add som ecode to handle error here and pass it to the main activity
                // ToDo Revert value when error occured
            }
        }
    }
}