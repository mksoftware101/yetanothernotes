package mk.software101.features.ui.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import mk.software101.features.login.R
import mk.software101.features.login.databinding.FragmentLoginBinding
import mk.software101.features.models.EmailValidationFailedReason
import mk.software101.features.models.PasswordValidationFailedReason
import mk.software101.features.ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel, LoginPartialState, LoginState>() {

    private val deepLinkNotesList get() = resources.getString(R.string.deepLinkNotesListUrl).toUri()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun initViewModel(): LoginViewModel =
        ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

    override fun setupUI() {
        binding.viewModel = viewModel
        binding.signUpTxt.paint?.isUnderlineText = true
        resetEmailError()
        resetPasswordError()
    }

    override fun render(viewState: LoginState) {
        with(viewState) {
            renderLoading(isLoading)
            renderEmailValidation(emailValidationFailedReasons)
            renderPasswordValidation(passwordValidationFailedReasons)

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

    private fun renderPasswordValidation(passwordValidationFailedReasons: Set<PasswordValidationFailedReason>?) {
        if (passwordValidationFailedReasons != null) {
            passwordValidationFailedReasons.forEach {
                when (it) {
                    PasswordValidationFailedReason.EMPTY_PASSWORD -> setEmptyPasswordError()
                    PasswordValidationFailedReason.INVALID_PASSWORD -> setInvalidPasswordError()
                }
            }
        } else {
            resetPasswordError()
        }
    }

    private fun renderEmailValidation(emailValidationFailedReasons: Set<EmailValidationFailedReason>?) {
        if (emailValidationFailedReasons != null) {
            emailValidationFailedReasons.forEach {
                when (it) {
                    EmailValidationFailedReason.EMPTY_EMAIL -> setEmptyEmailError()
                    EmailValidationFailedReason.INVALID_EMAIL -> setInvalidEmailError()
                }
            }
        } else {
            resetEmailError()
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

    private fun renderLoading(loadingVisible: Boolean) {
        binding.loadingView.visibility = if (loadingVisible) View.VISIBLE else View.GONE
    }

    private fun navigateToNotesListScreen() {
        findNavController().navigate(
            NavDeepLinkRequest.Builder.fromUri(deepLinkNotesList).build()
        )
    }

    private fun showLoginFailedSnackbar() {
        Snackbar.make(binding.loginContainer, R.string.loginLoginFailed, Snackbar.LENGTH_LONG)
            .show()
    }
}