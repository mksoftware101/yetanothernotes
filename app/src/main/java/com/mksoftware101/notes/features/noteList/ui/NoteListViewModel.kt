package com.mksoftware101.notes.features.noteList.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import com.mksoftware101.notes.BR
import com.mksoftware101.notes.R
import com.mksoftware101.notes.features.noteList.domain.GetObservableNoteListUseCase
import com.mksoftware101.notes.features.noteList.ui.extensions.toDisplay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getNoteListUseCase: GetObservableNoteListUseCase
) : ViewModel() {

    val display = Display()

    internal fun getNoteList() {
        viewModelScope.launch {
            try {
                getNoteListUseCase.run().collect { noteList ->
                    display.update(noteList.toDisplay())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    class Display {
        private val itemCallback = object : DiffUtil.ItemCallback<NoteListItemViewModel>() {
            override fun areItemsTheSame(
                oldItem: NoteListItemViewModel,
                newItem: NoteListItemViewModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: NoteListItemViewModel,
                newItem: NoteListItemViewModel
            ): Boolean {
                return oldItem.title == newItem.title &&
                        oldItem.creationDate == newItem.creationDate
            }

        }

        val items = AsyncDiffObservableList(itemCallback)
        val itemBinding = ItemBinding.of<NoteListItemViewModel>(BR.vm, R.layout.item_note_list)

        fun update(list: List<NoteListItemViewModel>) {
            items.update(list)
        }
    }
}