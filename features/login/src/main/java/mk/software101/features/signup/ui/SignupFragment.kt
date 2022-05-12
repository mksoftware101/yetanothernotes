package mk.software101.features.signup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import mk.software101.features.login.R
import mk.software101.features.login.databinding.FragmentSignupBinding
import mk.software101.features.signup.ui.states.UiState

class SignupFragment : Fragment() {

    private lateinit var viewModel: SignupViewModel
    private val uiStateObserver = Observer<UiState> { uiState ->
        when (uiState) {
            UiState.InvalidEmail -> handleInvalidEmail()
            UiState.InvalidOrNotSamePassword -> handleInvalidOrNotSamePassword()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentSignupBinding>(
            inflater,
            R.layout.fragment_signup,
            container,
            false
        ).also {
            it.passwordContainer.coroutineScope = lifecycleScope
            it.repeatPasswordContainer.coroutineScope = lifecycleScope
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        DataBindingUtil.getBinding<FragmentSignupBinding>(requireView())?.viewModel = viewModel
        viewModel.uiState.observe(viewLifecycleOwner, uiStateObserver)
    }

    private fun handleInvalidOrNotSamePassword() {
        TODO("Not yet implemented")
    }

    private fun handleInvalidEmail() {
        TODO("Not yet implemented")
    }
}