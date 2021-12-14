package com.mksoftware101.notes.features.noteList.domain

import com.mksoftware101.notes.core.repository.NotesRepository
import com.mksoftware101.notes.core.usecase.NoParamUseCase
import com.mksoftware101.notes.features.noteList.domain.extensions.toNoteList
import com.mksoftware101.notes.features.noteList.data.types.ObservableNoteList
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class GetObservableNotesListUseCase(
    private val repository: NotesRepository
) : NoParamUseCase<ObservableNoteList> {

    override fun run(): ObservableNoteList {
        return repository.getDbEntityList()
//            .filter { it.isNotEmpty() } // When list is empty, then loader doesn't hide because of this filter
            .map { it.toNoteList() }
    }
}