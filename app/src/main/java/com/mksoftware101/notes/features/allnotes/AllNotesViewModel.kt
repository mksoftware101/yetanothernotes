package com.mksoftware101.notes.features.allnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mksoftware101.notes.features.allnotes.domain.FetchNotesUseCase
import com.mksoftware101.notes.features.allnotes.types.Notes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AllNotesViewModel @Inject constructor(
    private val fetchNotesUseCase: FetchNotesUseCase
) : ViewModel() {

    private val _notes = MutableLiveData<Notes>()
    val notes: LiveData<Notes> = _notes

    init {
        viewModelScope.launch {
            try {
                fetchNotesUseCase.run().collect { notes ->
                    Timber.d("[d] Fetched: $notes")
                    _notes.postValue(notes)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}