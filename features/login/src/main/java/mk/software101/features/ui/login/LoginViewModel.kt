package mk.software101.features.ui.login

import androidx.lifecycle.viewModelScope
import com.mksoftware101.core.validator.EmailValidator
import com.mksoftware101.core.validator.PasswordValidator
import kotlinx.coroutines.launch
import mk.software101.features.common.BaseViewModel
import mk.software101.features.domain.LoginUseCase
import mk.software101.features.domain.ValidateCredentialsUseCase
import mk.software101.features.domain.ValidationFailedReason
import mk.software101.features.models.LoginSharedData
import timber.log.Timber

class LoginViewModel(private val loginUseCase: LoginUseCase) :
    BaseViewModel<LoginIntent, LoginAction, LoginState>() {

    private val validateCredentialsUseCase =
        ValidateCredentialsUseCase(EmailValidator(), PasswordValidator())

    override fun mapIntentToAction(intent: LoginIntent): LoginAction =
        when (intent) {
            is LoginIntent.Idle -> LoginAction.SetIdle
            is LoginIntent.LogIn -> LoginAction.ValidateCredentials(intent.data)
            is LoginIntent.ForgotPassword -> LoginAction.RecoverPassword
        }

    override fun handleAction(action: LoginAction) {
        when (action) {
            is LoginAction.SetIdle -> setIdle()
            is LoginAction.ValidateCredentials -> validateCredentials(action.data)
            is LoginAction.DoLogin -> doLogin(action.data)
            is LoginAction.RecoverPassword -> recoverPassword()
        }
    }

    private fun validateCredentials(data: LoginSharedData) {
        val result = validateCredentialsUseCase.run(data)
        if (result.success) {
            handleAction(LoginAction.DoLogin(data))
            return
        } else {
            handleValidationFailed(result.failedReasons)
        }
    }

    private fun handleValidationFailed(reasons: Set<ValidationFailedReason>?) {
        reasons?.forEach {
            when (it) {
                ValidationFailedReason.EMPTY_EMAIL -> {
                    _viewState.value = LoginState.EmptyEmail
                }
                ValidationFailedReason.INVALID_EMAIL -> {
                    _viewState.value = LoginState.InvalidEmail
                }
                ValidationFailedReason.EMPTY_PASSWORD -> {
                    _viewState.value = LoginState.EmptyPassword
                }
                ValidationFailedReason.INVALID_PASSWORD -> {
                    _viewState.value = LoginState.InvalidPassword
                }
            }
        }
    }

    private fun doLogin(data: LoginSharedData) {
        viewModelScope.launch {
            try {
                _viewState.value = LoginState.Loading
                val loginData = LoginSharedData(data.email, data.password)
                loginUseCase.run(loginData)
                _viewState.value = LoginState.LoginSucceeded
            } catch (e: Throwable) {
                Timber.e(e, "Login error")
                _viewState.value = LoginState.LoginFailed
            }
        }
    }

    private fun setIdle() {
        _viewState.value = LoginState.Idle
    }

    private fun recoverPassword() {
        TODO("Recover password not implemented yet")
    }
}