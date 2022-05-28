package mk.software101.features.ui.login

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.net.toUri
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import mk.software101.features.common.BaseFragment
import mk.software101.features.login.R
import mk.software101.features.login.databinding.FragmentLoginBinding
import mk.software101.features.models.LoginSharedData

class LoginFragment :
    BaseFragment<LoginIntent, LoginAction, LoginState, FragmentLoginBinding, LoginViewModel>() {

    private val deepLinkNotesList get() = resources.getString(R.string.deepLinkNotesListUrl).toUri()

    override fun getScreenViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentLoginBinding.inflate(inflater, container, false)

    override fun createViewModel(): LoginViewModel =
        ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.dispatchIntent(LoginIntent.Idle)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun render(viewState: LoginState) {
        when (viewState) {
            is LoginState.Idle -> {
            }
            is LoginState.Loading -> {
                showLoading(true)
            }
            is LoginState.EmptyEmail -> {
                setEmptyEmailError()
            }
            is LoginState.InvalidEmail -> {
                setInvalidEmailError()
            }
            is LoginState.EmptyPassword -> {
                setEmptyPasswordError()
            }
            is LoginState.InvalidPassword -> {
                setInvalidPasswordError()
            }
            is LoginState.LoginSucceeded -> {
                navigateToNotesListScreen()
            }
            is LoginState.LoginFailed -> {
                onLoginFailed()
            }
        }
    }

    override fun setupUI() {
        viewBinding.signUpTxt.paint?.isUnderlineText = true
    }

    override fun setupEvents() {
        setupLoginButtonEvent()
        setupEmailTextChangedEvent()
        setupPasswordTextChangedEvent()
        setupForgotPasswordEvent()
        setupNavigateToSignUpEvent()
    }

    private fun setupNavigateToSignUpEvent() {
        viewBinding.signUpTxt.setOnClickListener {
            navigateToSignupScreen()
        }
    }

    private fun setupLoginButtonEvent() {
        viewBinding.loginButton.setOnClickListener {
            val email = viewBinding.emailInput.editText.toString()
            val password = viewBinding.passwordInput.editText.toString()
            val loginData = LoginSharedData(email, password)
            viewModel.dispatchIntent(LoginIntent.LogIn(loginData))
        }
    }

    private fun setupEmailTextChangedEvent() {
        viewBinding.emailInput.editText?.doOnTextChanged { _, _, _, _ ->
            resetEmailError()
        }
    }

    private fun setupPasswordTextChangedEvent() {
        viewBinding.passwordInput.editText?.doOnTextChanged { _, _, _, _ ->
            resetPasswordError()
        }
    }

    private fun setupForgotPasswordEvent() {
        viewBinding.forgotPasswordTxt.setOnClickListener {
            viewModel.dispatchIntent(LoginIntent.ForgotPassword)
        }
    }

    private fun resetEmailError() {
        viewBinding.emailInput.error = null
    }

    private fun resetPasswordError() {
        viewBinding.passwordInput.error = null
    }

    private fun setEmptyEmailError() {
        viewBinding.emailInput.error = getString(R.string.loginEmptyEmailError)
    }

    private fun setInvalidEmailError() {
        viewBinding.emailInput.error = getString(R.string.loginInvalidEmailError)
    }

    private fun setEmptyPasswordError() {
        viewBinding.emailInput.error = getString(R.string.loginPasswordEmptyError)
    }

    private fun setInvalidPasswordError() {
        viewBinding.emailInput.error = getString(R.string.loginInvalidPasswordError)
    }

    private fun navigateToSignupScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
    }

    private fun showLoading(loadingVisible: Boolean) {
        viewBinding.loadingView.visibility = if (loadingVisible) View.VISIBLE else View.GONE
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
        Snackbar
            .make(viewBinding.loginContainer, R.string.loginLoginFailed, Snackbar.LENGTH_LONG)
            .apply {
                val params = view.layoutParams as FrameLayout.LayoutParams
                params.gravity = Gravity.TOP
            }.show()
    }
}