package com.mksoftware101.notes.features.allnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mksoftware101.notes.R
import com.mksoftware101.notes.core.db.NotesDb
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AllNotesFragment : Fragment() {

    private lateinit var viewModel: AllNotesViewModel

    @Inject lateinit var notesDb: NotesDb

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val res = notesDb.getNotesDao().getAllNotes()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AllNotesViewModel::class.java)
    }
}