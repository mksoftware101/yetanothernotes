package com.mksoftware101.notes.features.notes.ui

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mksoftware101.notes.BR
import com.mksoftware101.notes.R
import com.mksoftware101.notes.features.notes.domain.FetchNotesUseCase
import com.mksoftware101.notes.features.notes.types.Notes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val fetchNotesUseCase: FetchNotesUseCase
) : ViewModel() {

    val items = ObservableArrayList<ItemNotesViewModel>()
    val itemBinding = ItemBinding.of<ItemNotesViewModel>(BR.itemViewModel, R.layout.item_notes)

    private val _notes = MutableLiveData<Notes>()
    val notes: LiveData<Notes> = _notes

    init {
        viewModelScope.launch {
            try {
                fetchNotesUseCase.run().collect { notes ->
                    Timber.d("[d] Fetched: ${notes.size}, ${Thread.currentThread().name}")
                    val tempItems = notes.map {
                        ItemNotesViewModel(it.content.substring(0, 8), it.creationDate)
                    }
                    items.addAll(tempItems)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}