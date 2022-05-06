package com.mksoftware101.common.ui_components.input.username

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
    var userNameCallback: UserNameCallback? = null
    var lastUserName: String? = null

    private var validationJob: Job? = null

    fun onTextChanged(userName: CharSequence) {
        validationJob?.cancel()
        validationJob = viewModelScope.launch {
            resetError()
            delay(DELAY_MS)
            val userNameCandidate: String? =
                if (Patterns.EMAIL_ADDRESS.matcher(userName).matches()) {
                    userName.toString()
                } else {
                    setError()
                    null
                }

            if (userNameCandidate != lastUserName) {
                lastUserName = userNameCandidate
                userNameCallback?.onUserNameChanged(lastUserName)
            }
        }
    }

    private fun resetError() = usernameError.set(null)

    private fun setError() {
        usernameError.set("Wrong email address")
    }
}