package mk.software101.features.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import mk.software101.features.login.R
import mk.software101.features.login.databinding.FragmentSignupBinding
import mk.software101.features.ui.signup.states.UiState

class SignupFragment : Fragment() {

    private val deepLinkNotesList by lazy {
        resources.getString(R.string.deepLinkNotesListUrl).toUri()
    }
    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewModel: SignupViewModel
    private val uiStateObserver = Observer<UiState> { uiState ->
        when (uiState) {
            UiState.PasswordsNotSame -> {
                binding.repeatPasswordContainer.setPasswordsNotSameError()
            }
            UiState.SignUpSucceeded -> openNotesList()
            UiState.SignUpFailed -> showSignupError()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentSignupBinding>(
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
        viewModel =
            ViewModelProvider(this, SignUpViewModelFactory()).get(SignupViewModel::class.java)
        DataBindingUtil.getBinding<FragmentSignupBinding>(requireView())?.viewModel = viewModel
        viewModel.uiState.observe(viewLifecycleOwner, uiStateObserver)
    }

    private fun showSignupError() {
        Snackbar.make(
            binding.signupCoordinatorLayout,
            R.string.signupSignUpError,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun openNotesList() {
        findNavController().navigate(
            NavDeepLinkRequest.Builder.fromUri(deepLinkNotesList).build()
        )
    }
}