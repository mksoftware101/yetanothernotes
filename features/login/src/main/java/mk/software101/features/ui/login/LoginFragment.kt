package mk.software101.features.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import mk.software101.features.domain.EmailValidationFailedReason
import mk.software101.features.domain.PasswordValidationFailedReason
import mk.software101.features.login.R
import mk.software101.features.login.databinding.FragmentLoginBinding
import timber.log.Timber

class LoginFragment : Fragment() {

    private val deepLinkNotesList get() = resources.getString(R.string.deepLinkNotesListUrl).toUri()
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]
        viewModel.state.observe(viewLifecycleOwner) { state -> render(state) }
        viewModel.initialize()
        binding.viewModel = viewModel
        binding.signUpTxt.paint?.isUnderlineText = true
        resetEmailError()
        resetPasswordError()
    }

    private fun render(viewState: LoginState) {
        showLoading(viewState.isLoading)
        with(viewState) {
            if (viewState.emailValidationFailedReasons != null) {
                emailValidationFailedReasons?.forEach { it ->
                    when (it) {
                        EmailValidationFailedReason.EMPTY_EMAIL -> setEmptyEmailError()
                        EmailValidationFailedReason.INVALID_EMAIL -> setInvalidEmailError()
                    }
                }
            } else {
                resetEmailError()
            }

            if (viewState.passwordValidationFailedReasons != null) {
                passwordValidationFailedReasons?.forEach {
                    when (it) {
                        PasswordValidationFailedReason.EMPTY_PASSWORD -> setEmptyPasswordError()
                        PasswordValidationFailedReason.INVALID_PASSWORD -> setInvalidPasswordError()
                    }
                }
            } else {
                resetPasswordError()
            }

            if (isSignupClicked) {
                navigateToSignupScreen()
            }
            if (isLoginFailure) {
                showLoginFailedSnackbar()
            }
            if (isLoginSucceed) {
                navigateToNotesListScreen()
            }
        }
    }

    private fun setupNavigateToSignUpEvent() {
        binding.signUpTxt.setOnClickListener {
            navigateToSignupScreen()
        }
    }


    private fun setupEmailTextChangedEvent() {
        binding.emailInput.editText?.doOnTextChanged { _, _, _, _ ->
            resetEmailError()
        }
    }

    private fun setupPasswordTextChangedEvent() {
        binding.passwordInput.editText?.doOnTextChanged { _, _, _, _ ->
            resetPasswordError()
        }
    }

    private fun resetEmailError() {
        binding.emailInput.error = null
    }

    private fun resetPasswordError() {
        binding.passwordInput.error = null
    }

    private fun setEmptyEmailError() {
        binding.emailInput.error = getString(R.string.loginEmptyEmailError)
    }

    private fun setInvalidEmailError() {
        binding.emailInput.error = getString(R.string.loginInvalidEmailError)
    }

    private fun setEmptyPasswordError() {
        binding.passwordInput.error = getString(R.string.loginPasswordEmptyError)
    }

    private fun setInvalidPasswordError() {
        binding.passwordInput.error = getString(R.string.loginInvalidPasswordError)
    }

    private fun navigateToSignupScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
    }

    private fun showLoading(loadingVisible: Boolean) {
        binding.loadingView.visibility = if (loadingVisible) View.VISIBLE else View.GONE
    }

    private fun navigateToNotesListScreen() {
        findNavController().navigate(
            NavDeepLinkRequest.Builder.fromUri(deepLinkNotesList).build()
        )
    }

    private fun onLoginFailed() {
        showLoading(loadingVisible = false)
        showLoginFailedSnackbar()
    }

    private fun showLoginFailedSnackbar() {
        Snackbar.make(binding.loginContainer, R.string.loginLoginFailed, Snackbar.LENGTH_LONG)
            .show()
        // .apply {
        //                val params = view.layoutParams as FrameLayout.LayoutParams
        //                params.gravity = Gravity.TOP
        //            }
    }
}