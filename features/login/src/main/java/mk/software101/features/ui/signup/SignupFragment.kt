package mk.software101.features.ui.signup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import mk.software101.features.login.R
import mk.software101.features.login.databinding.FragmentSignupBinding
import mk.software101.features.models.EmailValidationFailedReason
import mk.software101.features.models.PasswordValidationFailedReason
import mk.software101.features.ui.base.BaseFragment

class SignupFragment :
    BaseFragment<FragmentSignupBinding, SignupViewModel, SignupPartialState, SignupState>() {

    private val deepLinkNotesList by lazy {
        resources.getString(R.string.deepLinkNotesListUrl).toUri()
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DataBindingUtil.inflate<FragmentSignupBinding>(
            inflater, R.layout.fragment_signup,
            container, false
        )

    override fun initViewModel(): SignupViewModel =
        ViewModelProvider(this, SignUpViewModelFactory()).get(SignupViewModel::class.java)

    override fun setupUI() {
        binding.viewModel = viewModel
        binding.passwordInput.errorIconDrawable = null
        binding.repeatPasswordInput.errorIconDrawable = null
    }

    override fun render(viewState: SignupState) {
        with(viewState) {
            renderLoading(isLoading)
            renderEmailValidation(emailValidationFailedReason)
            renderPasswordValidation(passwordValidationFailedReason)
            renderRepeatPasswordValidation(repeatPasswordValidationFailedReason)
            renderPasswordSame(isPasswordsTheSame)
            if (isSignupFailure) {
                showSignupError()
                binding.emailInput.requestFocus()
            }
            if (isSignupSucceed) {
                openNotesList()
            }
        }
    }

    private fun renderPasswordSame(isPasswordsTheSame: Boolean?) {
        isPasswordsTheSame?.let {
            if (it) {
                binding.passwordInput.error = null
                binding.repeatPasswordInput.error = null
            } else {
                binding.passwordInput.error = getString(R.string.signupPasswordNotTheSameError)
                binding.repeatPasswordInput.error = getString(R.string.signupPasswordNotTheSameError)
            }
        }
    }

    private fun renderRepeatPasswordValidation(repeatPasswordValidationFailedReason: Set<PasswordValidationFailedReason>?) {
        if (repeatPasswordValidationFailedReason != null) {
            repeatPasswordValidationFailedReason.forEach {
                when (it) {
                    PasswordValidationFailedReason.EMPTY_PASSWORD -> setEmptyRepeatPasswordError()
                    PasswordValidationFailedReason.INVALID_PASSWORD -> setInvalidRepeatPasswordError()
                }
            }
        } else {
            resetRepeatPasswordError()
        }
    }

    private fun setInvalidRepeatPasswordError() {
        binding.repeatPasswordInput.error = getString(R.string.signupInvalidPasswordError)
    }

    private fun setEmptyRepeatPasswordError() {
        binding.repeatPasswordInput.error = getString(R.string.signupEmptyPasswordError)
    }

    private fun resetRepeatPasswordError() {
        binding.repeatPasswordInput.error = null
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

    private fun resetPasswordError() {
        binding.passwordInput.error = null
    }

    private fun setInvalidPasswordError() {
        binding.passwordInput.error = getString(R.string.signupInvalidPasswordError)
    }

    private fun setEmptyPasswordError() {
        binding.passwordInput.error = getString(R.string.signupEmptyPasswordError)
    }

    private fun resetEmailError() {
        binding.emailInput.error = null
    }

    private fun setInvalidEmailError() {
        binding.emailInput.error = getString(R.string.signupInvalidEmailError)
    }

    private fun setEmptyEmailError() {
        binding.emailInput.error = getString(R.string.signupEmptyEmailError)
    }

    private fun renderLoading(loadingVisible: Boolean) {
        binding.loadingView.visibility = if (loadingVisible) View.VISIBLE else View.GONE
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