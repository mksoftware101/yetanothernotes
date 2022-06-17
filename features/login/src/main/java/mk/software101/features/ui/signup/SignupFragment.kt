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
import mk.software101.features.domain.EmailValidationFailedReason
import mk.software101.features.domain.PasswordValidationFailedReason
import mk.software101.features.login.R
import mk.software101.features.login.databinding.FragmentSignupBinding
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
    }

    override fun render(viewState: SignupState) {
        with(viewState) {
            renderLoading(isLoading)
            renderEmailValidation(emailValidationFailedReason)
            if (!isPasswordsSame) {
                renderPasswordSame(isPasswordsSame)
            } else {
                renderPasswordValidation(passwordValidationFailedReason)
                renderRepeatPasswordValidation(repeatPasswordValidationFailedReason)
            }
            if (isSignupFailure) {
                showSignupError()
                binding.emailInput.requestFocus()
            }
            if (isSignupSucceed) {
                openNotesList()
            }
        }
    }

    private fun renderPasswordSame(isPasswordsSame: Boolean) {
        if (isPasswordsSame) {
            binding.passwordInput.error = null
            binding.repeatPasswordInput.error = null
        } else {
            binding.passwordInput.error = "Passwords not the same"
            binding.repeatPasswordInput.error = "Passwords not the same"
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
        binding.repeatPasswordInput.error = "Invalid repeat password"
    }

    private fun setEmptyRepeatPasswordError() {
        binding.repeatPasswordInput.error = "EMpty repeat password"
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
        binding.passwordInput.error = "Invalid passwd"
    }

    private fun setEmptyPasswordError() {
        binding.passwordInput.error = "Empty passwd"
    }

    private fun resetEmailError() {
        binding.emailInput.error = null
    }

    private fun setInvalidEmailError() {
        binding.emailInput.error = "Invalid e-mail"
    }

    private fun setEmptyEmailError() {
        binding.emailInput.error = "Empty e-mail"
    }


    /*
        private val uiStateObserver = Observer<UiState> { uiState ->
            when (uiState) {
                UiState.EmptyEmail -> {
                    binding.userNameTxt.showError()
                }
                UiState.PasswordsNotSame -> {
                    binding.passwordContainer.highlightError()
                    binding.repeatPasswordContainer.showPasswordsNotSameError()
                }
                UiState.SignUpSucceeded -> openNotesList()
                UiState.SignUpFailed -> showSignupError()
            }
        }
    */
/*
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
*/
/*    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, SignUpViewModelFactory()).get(SignupViewModel::class.java)
        DataBindingUtil.getBinding<FragmentSignupBinding>(requireView())?.viewModel = viewModel
        viewModel.uiState.observe(viewLifecycleOwner, uiStateObserver)
    }
*/

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