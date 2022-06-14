package mk.software101.features.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mksoftware101.core.validator.EmailValidator
import com.mksoftware101.core.validator.PasswordValidator
import kotlinx.coroutines.launch
import mk.software101.features.domain.LoginUseCase
import mk.software101.features.domain.ValidateCredentialsUseCase
import mk.software101.features.domain.EmailValidationFailedReason
import mk.software101.features.domain.PasswordValidationFailedReason
import mk.software101.features.models.LoginSharedData

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val validateCredentialsUseCase =
        ValidateCredentialsUseCase(EmailValidator(), PasswordValidator())

    val emailObservable = ObservableField("")
    private val email get() = emailObservable.get()!!

    val passwordObservable = ObservableField("")
    private val password get() = passwordObservable.get()!!

    private val _state = MutableLiveData<LoginState>()
    val state: LiveData<LoginState> = _state
//        get() {
//            if (field.value == null) {
//                throw IllegalStateException("UI state wasn't initlialized. Have you run \"initialize\" method already?")
//            }
//            return field
//        }

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

    fun initialize() {
        emailObservable.set("")
        passwordObservable.set("")
        reduce(LoginPartialState.Init)
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

    private fun reduce(
        partialState: LoginPartialState,
        currentState: LoginState = _state.value ?: LoginState.initialize()
    ) {
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
}