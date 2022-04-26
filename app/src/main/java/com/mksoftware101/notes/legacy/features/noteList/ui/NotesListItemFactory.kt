package com.mksoftware101.notes.legacy.features.noteList.ui

import com.mksoftware101.notes.legacy.core.ui.ListItemFactory
import com.mksoftware101.notes.legacy.features.noteList.data.types.NoteList
import com.mksoftware101.notes.legacy.features.noteList.domain.UpdateNoteUseCase
import com.mksoftware101.notes.legacy.features.noteList.ui.communication.noteslistitem.Channels
import javax.inject.Inject

class NotesListItemFactory @Inject constructor(
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val channel: Channels
) : ListItemFactory<NoteList, List<NoteListItemViewModel>> {

    override fun assemble(data: NoteList): List<NoteListItemViewModel> {
        return data.map { note ->
            NoteListItemViewModel(note, updateNoteUseCase, channel)
        }
    }
}