package mk.software101.features.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mk.software101.features.domain.SignUpUseCase
import mk.software101.features.models.LoginSharedData
import mk.software101.features.ui.signup.states.UiState

class SignupViewModel(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private var emailAddress: String? = null
    private var password: String? = null
    private var repeatPassword: String? = null

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun onUserNameChanged(userName: String?) {
        emailAddress = userName
    }

    fun onPasswordChanged(password: String?) {
        this.password = password
    }

    fun onRepeatPasswordChanged(repeatPassword: String?) {
        this.repeatPassword = repeatPassword
    }

    fun onSignup() {
        if (matchPasswords()) {
            viewModelScope.launch {
                try {
                    signUpUseCase.run(LoginSharedData(emailAddress!!, password!!))
                    _uiState.value = UiState.SignUpSucceeded
                } catch (e: Throwable) {
                    e.printStackTrace() // ToDo Where is Timber?
                    _uiState.value = UiState.SignUpFailed
                }
            }
        }
    }

    private fun matchPasswords(): Boolean {
        if (isPasswordNotSame(password, repeatPassword)) {
            _uiState.value = UiState.PasswordsNotSame
            return false
        }

        return true
    }

    private fun isPasswordNotSame(password: String?, repeatPassword: String?) =
        password.isNullOrBlank() || repeatPassword.isNullOrBlank() || password != repeatPassword
}