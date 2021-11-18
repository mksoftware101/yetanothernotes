package com.mksoftware101.notes.features.noteList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.mksoftware101.notes.R
import com.mksoftware101.notes.databinding.FragmentNoteListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private val viewModel: NoteListViewModel by viewModels()
    private lateinit var viewBinding: FragmentNoteListBinding
    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, container, false)
        viewBinding.viewModel = viewModel
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObserveError()
        setupSwipeToRefresh()
    }

    private fun startObserveError() {
        viewModel.error.observe(viewLifecycleOwner, { isError ->
            if (isError) {
                showErrorSnackbar()
            } else {
                hideErrorSnackbar()
            }
        })
    }

    private fun setupSwipeToRefresh() {
        viewBinding.swipeToRefresh.setOnRefreshListener {
            viewBinding.swipeToRefresh.isRefreshing = false
            viewModel.refresh()
        }
    }

    private fun showErrorSnackbar() {
        errorSnackbar =
            Snackbar.make(viewBinding.root, R.string.snackbarDbError, Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbarRetryBtn) { viewModel.refresh() }
                .also { it.show() }
    }

    private fun hideErrorSnackbar() {
        errorSnackbar?.dismiss()
    }
}