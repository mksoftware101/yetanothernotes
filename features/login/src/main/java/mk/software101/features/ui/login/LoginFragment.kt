package mk.software101.features.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import mk.software101.features.login.R
import mk.software101.features.ui.login.states.LoginUiState
import mk.software101.features.login.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val deepLinkNotesList get() = resources.getString(R.string.deepLinkNotesListUrl).toUri()

    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val uiStateObserver = Observer<LoginUiState> { uiState ->
        when (uiState) {
            LoginUiState.EmptyEmailAddress -> {
                binding.usernameTxt.showError()
            }
            LoginUiState.EmptyPasswordField -> {
                binding.passwordTxt.showError()
            }
            LoginUiState.SignupClickedUiState -> navigateToSignupScreen()
            LoginUiState.LoginSucceeded -> navigateToNotesListScreen()
            LoginUiState.LoginFailed -> showLoginFailedSnackbar()
        }
    }

    private fun showLoginFailedSnackbar() {
        Snackbar
            .make(binding.loginContainer, R.string.loginLoginFailed, Snackbar.LENGTH_LONG)
            .show()
    }

    private fun navigateToNotesListScreen() {
        findNavController().navigate(
            NavDeepLinkRequest.Builder.fromUri(deepLinkNotesList).build()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginViewModel =
            ViewModelProvider(this, LoginViewModelFactory())
                .get(LoginViewModel::class.java)
                .apply {
                    uiState.observe(viewLifecycleOwner, uiStateObserver)
                }

        _binding = FragmentLoginBinding.inflate(inflater, container, false).also {
            it.viewModel = loginViewModel
            it.passwordTxt.coroutineScope = lifecycleScope
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToSignupScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
    }
}