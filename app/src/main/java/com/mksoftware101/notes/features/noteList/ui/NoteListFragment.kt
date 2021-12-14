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
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.BaseTransientBottomBar.Duration
import com.google.android.material.snackbar.Snackbar
import com.mksoftware101.notes.R
import com.mksoftware101.notes.databinding.FragmentNoteListBinding
import com.mksoftware101.notes.features.noteList.ui.communication.noteslistitem.AddToFavouriteFailed
import com.mksoftware101.notes.features.noteList.ui.communication.noteslistitem.Channels
import com.mksoftware101.notes.features.noteList.ui.communication.noteslistitem.RemoveFromFavouriteFailed
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private val viewModel: NoteListViewModel by viewModels()
    private val fabInterpolator = AccelerateDecelerateInterpolator()
    private lateinit var viewBinding: FragmentNoteListBinding

    @Inject lateinit var channels: Channels

    private val snackbarCallback = object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
        override fun onShown(snackbar: Snackbar?) {
            super.onShown(snackbar)
            moveFabUpWithAnimation(viewBinding.fab, snackbar)
        }

        override fun onDismissed(snackbar: Snackbar?, event: Int) {
            super.onDismissed(snackbar, event)
            moveFabDownWithAnimation(viewBinding.fab, snackbar)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setObserversToIdle()
    }

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
        setupCommunicationsChannels()
    }

    private fun setupCommunicationsChannels() {
        setupError()
        setupOutput()
    }

    private fun setupOutput() {
        channels.output.observe(viewLifecycleOwner, {

        })
    }

    private fun setupError() {
        channels.error.observe(viewLifecycleOwner, { state ->
            when(state) {
                is AddToFavouriteFailed -> showSnackbar(R.string.errorAddToFavourities, Snackbar.LENGTH_SHORT)
                is RemoveFromFavouriteFailed -> {}
            }
        })
    }

    private fun setupOnFlingListener() {
        viewBinding.notesRecyclerView.onFlingListener = object : RecyclerView.OnFlingListener() {
            override fun onFling(velocityX: Int, velocityY: Int): Boolean {
//                Timber.d("[d] $velocityY")
                if (velocityY > 0) {
                    viewBinding.fab.extend()
                } else if (velocityY < 0) {
                    viewBinding.fab.shrink()
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
                is GetNotesListError -> showSnackbarWithRetry()
                is RemoveNoteError -> showSnackbar(error.messageResId, Snackbar.LENGTH_LONG)
                is IdleNoteErrorState -> { /* Nothing to do */
                }
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
                is IdleNoteSuccessState -> { /* Nothing to do */
                }
            }
        })
    }

    private fun setupSwipeToRefresh() {
        viewBinding.swipeToRefresh.setOnRefreshListener {
            viewBinding.swipeToRefresh.isRefreshing = false
            viewModel.onRefresh()
        }
    }

    private fun showSnackbarWithRetry() {
        Snackbar.make(viewBinding.root, R.string.errorGetNotesList, Snackbar.LENGTH_LONG)
            .addCallback(snackbarCallback)
            .setAction(R.string.snackbarRetryBtn) { viewModel.onRefresh() }
            .also { it.show() }
    }

    private fun showSnackbar(@StringRes messageResId: Int, @Duration duration: Int) {
        Snackbar.make(viewBinding.root, messageResId, duration).addCallback(snackbarCallback).show()
    }

    private fun moveFabUpWithAnimation(fab: ExtendedFloatingActionButton, snackbar: Snackbar?) {
        with(fab) {
            val delta = translationY - (snackbar?.view?.height?.toFloat() ?: 0.0f)
            animate().translationY(delta).setInterpolator(fabInterpolator).start()
        }
    }

    private fun moveFabDownWithAnimation(fab: ExtendedFloatingActionButton, snackbar: Snackbar?) {
        with(fab) {
            val delta = translationY + (snackbar?.view?.height?.toFloat() ?: 0.0f)
            animate().translationY(delta).setInterpolator(fabInterpolator).start()
        }
    }
}