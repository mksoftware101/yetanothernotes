package com.mksoftware101.notes.splash

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mksoftware101.notes.R

class SplashFragment : Fragment() {
    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setFullScreenMode()
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun setFullScreenMode() =
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.postDelayed(object : Runnable {
            override fun run() {
                val direction = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                findNavController().navigate(direction)
            }
        }, 4000)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }
}