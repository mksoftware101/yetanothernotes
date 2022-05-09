package com.mksoftware101.common.ui_components.input.username

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mksoftware101.core.validator.Validator
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserNameViewModel(
    private val validator: Validator,
    private val errorText: String
) : ViewModel() {
    companion object {
        private const val DELAY_MS = 500L
    }

    val usernameError = ObservableField<String>()
    var usernameCallback: UserNameCallback? = null

    private var validationJob: Job? = null
    private var lastUserName: String? = null

    fun onTextChanged(userName: CharSequence) {
        validationJob?.cancel()
        validationJob = viewModelScope.launch {
            resetError()
            delay(DELAY_MS)
            val userNameCandidate: String? =
                if (validator.isValid(userName.toString())) {
                    userName.toString()
                } else {
                    setError()
                    null
                }

            if (userNameCandidate != lastUserName) {
                lastUserName = userNameCandidate
                usernameCallback?.onUserNameChanged(lastUserName)
            }
        }
    }

    private fun resetError() = usernameError.set(null)

    private fun setError() = usernameError.set(errorText)
}