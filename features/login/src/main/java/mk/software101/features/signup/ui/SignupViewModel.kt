package mk.software101.features.signup.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mk.software101.features.signup.models.SignUpData
import mk.software101.features.signup.domain.SignUpUseCase
import mk.software101.features.signup.ui.states.UiState

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
        if (emailAddress.isNullOrBlank() || isPasswordNotSame(password, repeatPassword)) {
            _uiState.value = UiState.IncorrectValuesInFields
            return
        }
        viewModelScope.launch {
            try {
                signUpUseCase.run(SignUpData(emailAddress!!, password!!))
                _uiState.value = UiState.SignUpSucceeded
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    private fun isPasswordNotSame(password: String?, repeatPassword: String?) =
        password.isNullOrBlank() || repeatPassword.isNullOrBlank() || password != repeatPassword
}

//                val user = ParseUser().apply {
//                    Log.d("TAG", "[d] email=$emailAddress, passwd=$password")
//                    username = "Just userName"
//                    email = emailAddress
//                    setPassword(password)
//                }.also {
//                    it.suspendSignUp()
//                }
//                ParseUser.logOut()
//Log.d("TAG", "[d] Before login")
////                ParseUser.logIn(emailAddress, password)
//ParseUser.logOut()
//Log.d(
//"TAG",
//"[d] Success? user=${ParseUser.getCurrentUser().sessionToken}"
//) // Success? user=r:e8b51a31cda86213afe19352a36d8925