package mk.software101.features.signup.ui

import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {

    val password = ObservableField<String>()
    val repeatPassword = ObservableField<String>()
    private var email: String? = null

    fun onUserNameChanged(userName: String?) {
        Log.d("TAG", "[d] SUCCESS, valid emial = $userName")
        email = userName
    }

    fun onPasswordChanged(password: String?) {
        Log.d("TAG", "[d] SUCCESS, valid password = $password")
    }

    fun onRepeatPasswordChanged(password: String?) {
        Log.d("TAG", "[d] SUCCESS, valid repeat password = $password")
    }

    fun onSignup() {
        Log.d("TAG", "[d][signup] password=${password.get()}, repeatPasswd=${repeatPassword.get()}")
    }
}