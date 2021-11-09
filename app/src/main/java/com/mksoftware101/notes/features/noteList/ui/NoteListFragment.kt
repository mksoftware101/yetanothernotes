package com.mksoftware101.notes.features.noteList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mksoftware101.notes.R
import com.mksoftware101.notes.databinding.FragmentNoteListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private lateinit var viewModel: NoteListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentNoteListBinding>(inflater, R.layout.fragment_note_list, container, false)
        viewModel = ViewModelProvider(this).get(NoteListViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root.rootView
    }
}