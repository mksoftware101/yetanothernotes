package com.mksoftware101.notes.features.noteList.ui

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mksoftware101.notes.features.noteList.data.Note
import com.mksoftware101.notes.features.noteList.data.types.NoteList
import com.mksoftware101.notes.features.noteList.domain.GetObservableNotesListUseCase
import com.mksoftware101.notes.features.noteList.domain.RemoveNoteUseCase
import com.mksoftware101.notes.features.noteList.domain.extensions.isIndexOutOfRange
import com.mksoftware101.notes.features.noteList.ui.extensions.toItemsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getNotesListUseCase: GetObservableNotesListUseCase,
    private val removeNoteUseCase: RemoveNoteUseCase,
) : ViewModel() {

    val recyclerViewHelper = NotesListRecyclerViewHelper()
    var loading = ObservableBoolean(false)

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val notesList = mutableListOf<Note>()

    init {
        getNotesList()
    }

    fun onRefresh() {
        getNotesList()
    }

    fun onRemove(index: Int) {
        if (notesList.isIndexOutOfRange(index))
            return // ToDo Send error message

        recyclerViewHelper.remove(index)
        viewModelScope.launch {
            try {
                removeNoteUseCase.run(notesList[index])
            } catch (e: Exception) {
                e.printStackTrace() // ToDo Send error message and log error by Timber
            }
        }
    }

    private fun getNotesList() {
        viewModelScope.launch {
            try {
                getNotesListUseCase.run()
                    .onStart {
                        loading.set(true)
                        _error.value = false
                    }.collect { list ->
                        loading.set(false)
                        update(list)
                    }
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = true
            }
        }
    }

    private fun update(list: NoteList) {
        with(notesList) {
            clear()
            addAll(list)
        }
        recyclerViewHelper.items.update(list.toItemsViewModel())
    }
}