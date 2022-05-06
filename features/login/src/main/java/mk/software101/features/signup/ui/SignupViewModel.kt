package mk.software101.features.signup.ui

import android.util.Log
import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class SignupViewModel : ViewModel() {

//    val emailError = ObservableField<String>()
//    private var delayEmailValidationJob: Job? = null

    fun onSignup() {
        Log.d("TAG", "[d] Will do sign up")
    }

    fun onEmailChanged(email: String?) {
        Log.d("TAG", "[d] SUCCESS, valid emial = $email")
//        delayEmailValidationJob?.cancel()
//        delayEmailValidationJob = viewModelScope.launch {
//            emailError.set(null)
//            delay(500)
//            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                Log.d("TAG", "[d] Email changed = $email")
//            } else {
//                emailError.set("Wrong email address")
//            }
    }
}