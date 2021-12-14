package com.mksoftware101.notes.features.noteList.ui

import androidx.recyclerview.widget.DiffUtil
import com.mksoftware101.notes.BR
import com.mksoftware101.notes.R
import com.mksoftware101.notes.features.noteList.domain.extensions.isIndexOutOfRange
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList

class NotesListRecyclerViewHelper {
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
                    oldItem.isFavourite == newItem.isFavourite &&
                    oldItem.letter == newItem.letter &&
                    oldItem.creationDate == newItem.creationDate
        }

    }

    val items = AsyncDiffObservableList(itemCallback)
    val itemBinding = ItemBinding.of<NoteListItemViewModel>(BR.vm, R.layout.notes_list_item)

    fun remove(index: Int) {
        if (items.toList().isIndexOutOfRange(index))
            return

        val modifiedItems = mutableListOf<NoteListItemViewModel>().apply {
            addAll(items.toList())
            removeAt(index)
        }
        items.update(modifiedItems)
    }
}