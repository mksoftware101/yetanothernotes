package com.mksoftware101.notes.legacy.features.noteList.ui

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mksoftware101.notes.R
import com.mksoftware101.notes.legacy.features.noteList.data.Note
import com.mksoftware101.notes.legacy.features.noteList.data.types.NoteList
import com.mksoftware101.notes.legacy.features.noteList.domain.GetObservableNotesListUseCase
import com.mksoftware101.notes.legacy.features.noteList.domain.RemoveNoteUseCase
import com.mksoftware101.notes.legacy.features.noteList.domain.extensions.isIndexOutOfRange
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getNotesListUseCase: GetObservableNotesListUseCase,
    private val removeNoteUseCase: RemoveNoteUseCase,
    private val notesListItemFactory: NotesListItemFactory,
) : ViewModel() {

    val recyclerViewHelper = NotesListRecyclerViewHelper()
    var loading = ObservableBoolean(false)

    private val _error = MutableLiveData<NotesListErrorState>()
    val error: LiveData<NotesListErrorState> = _error

    private val _success = MutableLiveData<NotesListSuccessState>()
    val success: LiveData<NotesListSuccessState> = _success

    private val notesList = mutableListOf<Note>()

    init {
        getNotesList()
    }

    fun onRefresh() {
        getNotesList()
    }

    fun onAdd() {

    }

    fun setObserversToIdle() {
        _success.value = IdleNoteSuccessState
        _error.value = IdleNoteErrorState
    }

    fun onRemove(index: Int) {
        if (notesList.isIndexOutOfRange(index)) {
            RemoveNoteError(R.string.errorRemoveNoteGeneral)
            return
        }

        recyclerViewHelper.remove(index)
        viewModelScope.launch {
            try {
                removeNoteUseCase.run(notesList[index])
                _success.value = RemoveNoteSuccessState
            } catch (e: Exception) {
                Timber.e(e, "Error while remove note from db")
                RemoveNoteError(R.string.errorRemoveNoteFromDb)
                // ToDo Revert item when error occured
            }
        }
    }

    private fun getNotesList() {
        viewModelScope.launch {
            try {
                getNotesListUseCase.run()
                    .onStart {
                        loading.set(true)
                    }.collect { list ->
                        loading.set(false)
                        update(list)
                    }
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = GetNotesListError
            }
        }
    }

    private fun update(list: NoteList) {
        with(notesList) {
            clear()
            addAll(list)
        }
        recyclerViewHelper.items.update(
            notesListItemFactory.assemble(list)
        )
    }
}