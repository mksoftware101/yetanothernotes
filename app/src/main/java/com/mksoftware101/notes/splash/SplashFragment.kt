package com.mksoftware101.notes.splash

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.mksoftware101.notes.R

class SplashFragment : Fragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        // TODO: Use the ViewModel
    }

}