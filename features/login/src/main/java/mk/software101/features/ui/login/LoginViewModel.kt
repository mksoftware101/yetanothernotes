package mk.software101.features.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mk.software101.features.domain.LoginUseCase
import mk.software101.features.models.LoginSharedData
import mk.software101.features.ui.login.states.LoginUiState
import timber.log.Timber

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {
    private var emailAddress: String? = null
    private var password: String? = null

    private val _uiState = MutableLiveData<LoginUiState>()
    val uiState: LiveData<LoginUiState> = _uiState

    fun onEmailChanged(email: String?) {
        this.emailAddress = email
    }

    fun onPasswordChanged(password: String) {
        this.password = password
    }

    fun onForgotPassword() {

    }

    fun onLogin() {
        checkEmailAndPasswordsThen {
            viewModelScope.launch {
                try {
                    val loginData = LoginSharedData(emailAddress!!, password!!)
                    loginUseCase.run(loginData)
                    _uiState.value = LoginUiState.LoginSucceeded
                } catch (e: Throwable) {
                    _uiState.value = LoginUiState.LoginFailed
                    Timber.e(e, "Login error")
                }
            }
        }
    }

    fun onSignupScreenClicked() {
        _uiState.value = LoginUiState.SignupClickedUiState
    }

    private fun checkEmailAndPasswordsThen(doNext: () -> Unit) {
        if (emailAddress.isNullOrEmpty()) {
            _uiState.value = LoginUiState.EmptyEmailAddress
            return
        }

        if (password.isNullOrEmpty()) {
            _uiState.value = LoginUiState.EmptyPasswordField
            return
        }

        doNext()
    }
}