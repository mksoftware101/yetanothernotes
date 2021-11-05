package com.mksoftware101.notes.features.notes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mksoftware101.notes.R
import com.mksoftware101.notes.databinding.FragmentNotesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentNotesBinding>(inflater, R.layout.fragment_notes, container, false)
        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        binding.notesViewModel = viewModel
        return binding.root.rootView
    }
}