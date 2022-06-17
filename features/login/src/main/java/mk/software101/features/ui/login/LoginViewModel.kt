package mk.software101.features.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.mksoftware101.core.validator.EmailValidator
import com.mksoftware101.core.validator.PasswordValidator
import kotlinx.coroutines.launch
import mk.software101.features.domain.LoginUseCase
import mk.software101.features.domain.ValidateCredentialsUseCase
import mk.software101.features.domain.ValidationHelper
import mk.software101.features.models.LoginSharedData
import mk.software101.features.ui.base.BaseViewModel

class LoginViewModel(private val loginUseCase: LoginUseCase) :
    BaseViewModel<LoginPartialState, LoginState>() {

    private val validateCredentialsUseCase =
        ValidateCredentialsUseCase(ValidationHelper(EmailValidator(), PasswordValidator()))

    val emailObservable = ObservableField("")
    private val email get() = emailObservable.get()!!

    val passwordObservable = ObservableField("")
    private val password get() = passwordObservable.get()!!

    override fun initialize() {
        emailObservable.set("")
        passwordObservable.set("")
        reduce(LoginPartialState.Init)
    }

    override fun reduce(partialState: LoginPartialState) {
        val currentState: LoginState = _state.value ?: LoginState.initialize()
        when (partialState) {
            is LoginPartialState.Init -> {
                _state.value = LoginState.initialize()
            }
            is LoginPartialState.ValidationFailed -> {
                _state.value =
                    currentState.copy(
                        emailValidationFailedReasons = partialState.validationResult.emailFailedReasons,
                        passwordValidationFailedReasons = partialState.validationResult.passwordFailedReasons
                    )
            }
            is LoginPartialState.LoadingVisible -> {
                _state.value = currentState.copy(
                    isLoading = true,
                    isLoginFailure = false,
                    emailValidationFailedReasons = null,
                    passwordValidationFailedReasons = null
                )
            }
            is LoginPartialState.LoginSucceed -> {
                _state.value = currentState.copy(isLoading = false, isLoginSucceed = true)
            }
            is LoginPartialState.LoginFailed -> {
                _state.value = currentState.copy(isLoading = false, isLoginFailure = true)
            }
            is LoginPartialState.SignupClicked -> {
                _state.value =
                    currentState.copy(
                        isSignupClicked = true,
                        emailValidationFailedReasons = null,
                        passwordValidationFailedReasons = null
                    )
            }
            is LoginPartialState.EmailTextChanged -> {
                _state.value = currentState.copy(emailValidationFailedReasons = null)
            }
            is LoginPartialState.PasswordTextChanged -> {
                _state.value = currentState.copy(passwordValidationFailedReasons = null)
            }
        }
    }

    fun onLogin() {
        val data = LoginSharedData(email, password)
        val result = validateCredentialsUseCase.run(data)
        if (result.success) {
            doLogin(data)
        } else {
            reduce(partialState = LoginPartialState.ValidationFailed(result))
        }
    }

    fun onSignup() {
        reduce(LoginPartialState.SignupClicked)
    }

    fun onRecoverPassword() {
        TODO("Recover password not implemented yet")
    }

    fun onEmailTextChanged() {
        reduce(LoginPartialState.EmailTextChanged)
    }

    fun onPasswordTextChanged() {
        reduce(LoginPartialState.PasswordTextChanged)
    }

    private fun doLogin(data: LoginSharedData) {
        viewModelScope.launch {
            try {
                reduce(LoginPartialState.LoadingVisible)
                val loginData = LoginSharedData(data.email, data.password)
                loginUseCase.run(loginData)
                reduce(LoginPartialState.LoginSucceed)
            } catch (e: Throwable) {
                reduce(LoginPartialState.LoginFailed)
            }
        }
    }
}