package mk.software101.features.ui.signup

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mk.software101.features.domain.SignUpUseCase
import mk.software101.features.domain.ValidateSignupInputsUseCase
import mk.software101.features.models.LoginSharedData
import mk.software101.features.ui.base.BaseViewModel

class SignupViewModel(
    private val validateSignupInputsUseCase: ValidateSignupInputsUseCase,
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel<SignupPartialState, SignupState>() {

    val emailObservable = ObservableField("")
    private val email get() = emailObservable.get()!!

    val passwordObservable = ObservableField("")
    private val password get() = passwordObservable.get()!!

    val repeatPasswordObservable = ObservableField("")
    private val repeatPassword get() = repeatPasswordObservable.get()!!

    override fun initialize() {
        emailObservable.set("")
        passwordObservable.set("")
        repeatPasswordObservable.set("")
        reduce(SignupPartialState.Init)
    }

    override fun reduce(partialState: SignupPartialState) {
        val currentState = state.value ?: SignupState.initialize()
        when (partialState) {
            SignupPartialState.Init -> {
                SignupState.initialize().emit()
            }
            SignupPartialState.SignupFailed -> {
                currentState.copy(
                    isLoading = false,
                    isSignupFailure = true,
                    isPasswordsTheSame = true,
                    emailValidationFailedReason = null,
                    passwordValidationFailedReason = null
                ).emit()
            }
            SignupPartialState.SignupSucceed -> {
                currentState.copy(isSignupFailure = false, isSignupSucceed = true).emit()
            }
            is SignupPartialState.ValidationFailed -> {
                currentState.copy(
                    isLoading = false,
                    isSignupFailure = false,
                    isPasswordsTheSame = partialState.result.isPasswordsTheSame,
                    emailValidationFailedReason = partialState.result.emailFailedReasons,
                    passwordValidationFailedReason = partialState.result.passwordFailedReasons,
                    repeatPasswordValidationFailedReason = partialState.result.repeatPasswordFailedReasons
                ).emit()
            }
            SignupPartialState.EmailTextChanged -> {
                currentState.copy(emailValidationFailedReason = null).emit()
            }
            SignupPartialState.LoadingVisible -> {
                currentState.copy(isLoading = true).emit()
            }
            SignupPartialState.PasswordTextChanged -> {
                currentState.copy(isPasswordsTheSame = null, passwordValidationFailedReason = null).emit()
            }
            SignupPartialState.RepeatPasswordChanged -> {
                currentState.copy(isPasswordsTheSame = null, repeatPasswordValidationFailedReason = null).emit()
            }
        }
    }

    fun onEmailTextChanged() {
        reduce(SignupPartialState.EmailTextChanged)
    }

    fun onPasswordTextChanged() {
        reduce(SignupPartialState.PasswordTextChanged)
    }

    fun onRepeatPasswordChanged() {
        reduce(SignupPartialState.RepeatPasswordChanged)
    }

    fun onSignup() {
        val result = validateSignupInputsUseCase.run(email, password, repeatPassword)
        if (result.success) {
            doSignup()
        } else {
            reduce(SignupPartialState.ValidationFailed(result))
        }
    }

    fun doSignup() {
        viewModelScope.launch {
            try {
                reduce(SignupPartialState.LoadingVisible)
                signUpUseCase.run(LoginSharedData(email, password))
                reduce(SignupPartialState.SignupSucceed)
            } catch (e: Throwable) {
                reduce(SignupPartialState.SignupFailed)
            }
        }
    }

    private fun SignupState.emit() {
        _state.value = this
    }
}