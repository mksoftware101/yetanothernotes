package com.mksoftware101.notes.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mksoftware101.notes.R

class SplashFragment : Fragment() {

    companion object {
        const val DELAY_MS = 4000L
    }

    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setFullScreenMode()
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.postDelayed(object : Runnable {
            override fun run() {
                navigateHomeScreen()
            }
        }, DELAY_MS)
    }

    private fun navigateHomeScreen() {
        val direction = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
        findNavController().navigate(direction)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    private fun setFullScreenMode() =
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
}