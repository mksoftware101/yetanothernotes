package com.mksoftware101.notes.features.noteList.ui

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mksoftware101.notes.features.noteList.data.Note
import com.mksoftware101.notes.features.noteList.domain.UpdateNoteUseCase
import com.mksoftware101.notes.features.noteList.ui.communication.noteslistitem.AddToFavouriteFailed
import com.mksoftware101.notes.features.noteList.ui.communication.noteslistitem.Channels
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class NoteListItemViewModel(
    private var note: Note,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val errorChannel: Channels
) : ViewModel() {

    val title: String
        get() = note.content

    val letter: String
        get() = note.content.take(1)

    val creationDate: String
        get() = note.creationDate

    val isFavourite: Boolean
        get() {
            return note.isFavourite
        }

    val id: Long
        get() = note.id

    val toggle: ObservableBoolean = ObservableBoolean(false)

    fun onClick() {
        // ToDo Navigate to details screen
    }

    fun onFavouriteChange() {
        viewModelScope.launch {
            try {
                note = note.copy(isFavourite = note.isFavourite)
                updateNoteUseCase.run(note)
            } catch (e: Exception) {
                Timber.e(e, "Error while add note to favourites")
                errorChannel.emitError(AddToFavouriteFailed)
                toggle.set(false)
            }
        }
    }
}