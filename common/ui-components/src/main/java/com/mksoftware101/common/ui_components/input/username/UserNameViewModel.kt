package com.mksoftware101.common.ui_components.input.username

import android.util.Log
import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserNameViewModel : ViewModel() {
    companion object {
        private const val DELAY_MS = 500L
    }

    val usernameError = ObservableField<String>()
    val usernameHint = ObservableField<String>("Email")
    var validUserNameCallback: ValidUserNameCallback? = null

    private var validationJob: Job? = null

    fun onTextChanged(userName: CharSequence) {
        validationJob?.cancel()
        validationJob = viewModelScope.launch {
            resetError()
            delay(DELAY_MS)
            if (Patterns.EMAIL_ADDRESS.matcher(userName).matches()) {
                Log.d("TAG", "[d] Email changed = $userName")
                validUserNameCallback?.onValidUserName(userName.toString())
            } else {
                setError()
            }
        }
    }

    private fun resetError() = usernameError.set(null)

    private fun setError() {
        usernameError.set("Wrong email address")
    }
}