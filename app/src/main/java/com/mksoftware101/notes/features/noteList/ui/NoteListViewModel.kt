package com.mksoftware101.notes.features.noteList.ui

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mksoftware101.notes.BR
import com.mksoftware101.notes.R
import com.mksoftware101.notes.features.noteList.domain.GetObservableNoteListUseCase
import com.mksoftware101.notes.features.noteList.ui.extensions.toDisplay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getNoteListUseCase: GetObservableNoteListUseCase
) : ViewModel() {

    val displayItems = ObservableArrayList<NoteListItemViewModel>()
    val displayItemBinding = ItemBinding.of<NoteListItemViewModel>(BR.vm, R.layout.item_note_list)

    internal fun getNoteList() {
        viewModelScope.launch {
            try {
                getNoteListUseCase.run().collect { noteList ->
                    with(displayItems) {
                        clear()
                        addAll(noteList.toDisplay())
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}