package com.mksoftware101.common.ui_components.input.username

interface UserNameCallback {
    /**
     * Called when userName has changed.
     *
     * It's called when username has changed from invalid to valid and  vice versa.
     * UserName decide if typed text is valid or not based on Validator. See [UserNameViewModel]
     *
     */
    fun onUserNameChanged(userName: String?)
}