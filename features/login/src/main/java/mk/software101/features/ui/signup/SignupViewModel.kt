package mk.software101.features.ui.signup

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mk.software101.features.domain.SignUpUseCase
import mk.software101.features.models.LoginSharedData
import mk.software101.features.ui.signup.states.UiState
import timber.log.Timber

class SignupViewModel(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    val emailObservable = ObservableField("")
    private val email get() = emailObservable.get()!!

    val passwordObservable = ObservableField("")
    private val password get() = passwordObservable.get()!!

    val repeatPasswordObservable = ObservableField("")
    private val repeatPassword get() = passwordObservable.get()!!

//    private var emailAddress: String? = null
//    private var repeatPassword: String? = null


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


    fun onEmailTextChanged() {

    }


    fun onPasswordTextChanged() {

    }

    fun onSignup() {
        checkEmailAndPasswordsThen {
            viewModelScope.launch {
                try {
                    signUpUseCase.run(LoginSharedData(emailAddress!!, password!!))
                    _uiState.value = UiState.SignUpSucceeded
                } catch (e: Throwable) {
                    _uiState.value = UiState.SignUpFailed
                    Timber.e(e, "Sign up error")
                }
            }
        }
    }

    private fun checkEmailAndPasswordsThen(doNext: () -> Unit) {
        if (emailAddress.isNullOrEmpty()) {
            _uiState.value = UiState.EmptyEmail
            return
        }

        if (!isPasswordsTheSame(password, repeatPassword)) {
            _uiState.value = UiState.PasswordsNotSame
            return
        }

        doNext()
    }

    private fun isPasswordsTheSame(password: String?, repeatPassword: String?) =
        !password.isNullOrBlank() && !repeatPassword.isNullOrBlank() && password == repeatPassword
}