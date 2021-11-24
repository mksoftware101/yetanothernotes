package com.mksoftware101.notes.features.noteList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mksoftware101.notes.R
import com.mksoftware101.notes.databinding.FragmentNoteListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private val viewModel: NoteListViewModel by viewModels()
    private lateinit var viewBinding: FragmentNoteListBinding

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
        setupSwipeToDelete()
    }

    private fun setupSwipeToDelete() {
        val itemTouchHelper = ItemTouchHelper(object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.onRemove(viewHolder.adapterPosition)
            }
        })
        itemTouchHelper.attachToRecyclerView(viewBinding.notesRecyclerView)
    }

    private fun startObserveError() {
        viewModel.error.observe(viewLifecycleOwner, { error ->
            when (error) {
                is GetNotesListError -> showErrorSnackbar()
                is RemoveNoteError -> showErrorSnackbar(error.messageResId)
            }
        })
    }

    private fun setupSwipeToRefresh() {
        viewBinding.swipeToRefresh.setOnRefreshListener {
            viewBinding.swipeToRefresh.isRefreshing = false
            viewModel.onRefresh()
        }
    }

    private fun showErrorSnackbar() {
        Snackbar.make(viewBinding.root, R.string.errorGetNotesList, Snackbar.LENGTH_LONG)
            .setAction(R.string.snackbarRetryBtn) { viewModel.onRefresh() }
            .also { it.show() }
    }

    private fun showErrorSnackbar(@StringRes messageResId: Int) {
        Snackbar.make(viewBinding.root, messageResId, Snackbar.LENGTH_LONG).show()
    }
}