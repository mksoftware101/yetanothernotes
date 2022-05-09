package mk.software101.features.signup.ui

import android.util.Log
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {

    fun onSignup() {
        Log.d("TAG", "[d] Will do sign up")
    }

    fun onUserNameChanged(userName: String?) {
        Log.d("TAG", "[d] SUCCESS, valid emial = $userName")
    }
}