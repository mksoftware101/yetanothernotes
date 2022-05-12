package mk.software101.features.signup.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mk.software101.features.signup.ui.states.UiState

class SignupViewModel : ViewModel() {

    private var email: String? = null
    private var password: String? = null
    private var repeatPassword: String? = null

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun onUserNameChanged(userName: String?) {
        email = userName
    }

    fun onPasswordChanged(password: String?) {
        this.password = password
    }

    fun onRepeatPasswordChanged(repeatPassword: String?) {
        this.repeatPassword = repeatPassword
    }

    fun onSignup() {
        if (email.isNullOrBlank()) {
            _uiState.value = UiState.InvalidEmail
            return
        }
        if (isPasswordNotSame(password, repeatPassword)) {
            _uiState.value = UiState.InvalidOrNotSamePassword
            return
        }
        // ToDo Run signIn
    }

    private fun isPasswordNotSame(password: String?, repeatPassword: String?) =
        password != repeatPassword
}