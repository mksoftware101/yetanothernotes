package com.mksoftware101.notes.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mksoftware101.notes.R
import timber.log.Timber

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        closeFullScreenMode()
        setWhiteStatusBarColor()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
        viewModel.state.observe(this, ::render)
        viewModel.fetchAllNotes()
    }

    private fun render(homeState: HomeState) {
        when (homeState) {
            is AllNotesFetchedState -> renderAllNotes(homeState)
        }
    }

    private fun renderAllNotes(homeState: AllNotesFetchedState) {
        Timber.d("[d] $homeState")
    }

    private fun setWhiteStatusBarColor() {
        requireActivity().window.statusBarColor = context?.getColor(R.color.white)!!
    }

    private fun closeFullScreenMode() =
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }
}