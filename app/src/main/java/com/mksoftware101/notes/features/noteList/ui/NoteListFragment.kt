package com.mksoftware101.notes.features.noteList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.BaseTransientBottomBar.Duration
import com.google.android.material.snackbar.Snackbar
import com.mksoftware101.notes.R
import com.mksoftware101.notes.databinding.FragmentNoteListBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private val viewModel: NoteListViewModel by viewModels()
    private val fabInterpolator = AccelerateDecelerateInterpolator()
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
        startObserveErrors()
        startObserveSuccess()
        setupSwipeToRefresh()
        setupSwipeToDelete()
        setupOnFlingListener()
    }

    private fun setupOnFlingListener() {
        viewBinding.notesRecyclerView.onFlingListener = object : RecyclerView.OnFlingListener() {
            override fun onFling(velocityX: Int, velocityY: Int): Boolean {
                if (velocityY > 0) {
                    viewBinding.fab.shrink()
                } else if (velocityY < 0) {
                    viewBinding.fab.extend()
                }
                return true
            }
        }
    }

    private fun setupSwipeToDelete() {
        val itemTouchHelper = ItemTouchHelper(object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.onRemove(viewHolder.adapterPosition)
            }
        })
        itemTouchHelper.attachToRecyclerView(viewBinding.notesRecyclerView)
    }

    private fun startObserveErrors() {
        viewModel.error.observe(viewLifecycleOwner, { error ->
            when (error) {
                is GetNotesListError -> showSnackbar()
                is RemoveNoteError -> showSnackbar(error.messageResId, Snackbar.LENGTH_LONG)
            }
        })
    }

    private fun startObserveSuccess() {
        viewModel.success.observe(viewLifecycleOwner, { success ->
            when (success) {
                is RemoveNoteSuccessState -> showSnackbar(
                    R.string.successRemoveNote,
                    Snackbar.LENGTH_SHORT
                )
            }
        })
    }

    private fun setupSwipeToRefresh() {
        viewBinding.swipeToRefresh.setOnRefreshListener {
            viewBinding.swipeToRefresh.isRefreshing = false
            viewModel.onRefresh()
        }
    }

    private fun showSnackbar() {
        Snackbar.make(viewBinding.root, R.string.errorGetNotesList, Snackbar.LENGTH_LONG)
            .setAction(R.string.snackbarRetryBtn) { viewModel.onRefresh() }
            .also { it.show() }
    }

    private fun showSnackbar(@StringRes messageResId: Int, @Duration duration: Int) {
        val snackbar = Snackbar.make(viewBinding.root, messageResId, duration)
        snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
            override fun onShown(transientBottomBar: Snackbar?) {
                super.onShown(transientBottomBar)
                val delta: Float = viewBinding.fab.translationY - snackbar.view.height
                viewBinding.fab
                    .animate().translationY(delta).setInterpolator(fabInterpolator).start()
            }

            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                val delta: Float = viewBinding.fab.translationY + snackbar.view.height
                viewBinding.fab
                    .animate().translationY(delta).setInterpolator(fabInterpolator).start()
            }
        })
        snackbar.show()
    }
}