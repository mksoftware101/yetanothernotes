package com.mksoftware101.notes.features.noteList.ui

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import com.mksoftware101.notes.BR
import com.mksoftware101.notes.R
import com.mksoftware101.notes.features.noteList.domain.GetObservableNoteListUseCase
import com.mksoftware101.notes.features.noteList.ui.extensions.toDisplay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getNoteListUseCase: GetObservableNoteListUseCase
) : ViewModel() {

    val display = Display()

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    init {
        getNoteList()
    }

    fun refresh() {
        getNoteList()
    }

    private fun getNoteList() {
        viewModelScope.launch {
            try {
                getNoteListUseCase.run()
                    .onStart {
                        display.loading = true
                        _error.value = false
                    }.collect { noteList ->
                        with(display) {
                            loading = false
                            update(noteList.toDisplay())
                        }
                    }
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = true
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
        val itemBinding = ItemBinding.of<NoteListItemViewModel>(BR.vm, R.layout.notes_list_item)

        var loadingObservable = ObservableBoolean(false)

        var loading: Boolean = false
            set(value) {
                field = value
                loadingObservable.set(value)
            }

        fun update(list: List<NoteListItemViewModel>) {
            items.update(list)
        }
    }
}