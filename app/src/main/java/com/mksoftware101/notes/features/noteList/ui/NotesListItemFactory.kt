package com.mksoftware101.notes.features.noteList.ui

import com.mksoftware101.notes.core.ui.ListItemFactory
import com.mksoftware101.notes.features.noteList.data.types.NoteList
import com.mksoftware101.notes.features.noteList.domain.UpdateNoteUseCase
import javax.inject.Inject

class NotesListItemFactory @Inject constructor(
    private val updateNoteUseCase: UpdateNoteUseCase
) : ListItemFactory<NoteList, List<NoteListItemViewModel>> {

    override fun assemble(data: NoteList): List<NoteListItemViewModel> {
        return data.map { note ->
            NoteListItemViewModel(note, updateNoteUseCase)
        }
    }
}