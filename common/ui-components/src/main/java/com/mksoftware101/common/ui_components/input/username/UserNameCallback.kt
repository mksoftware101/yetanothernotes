package com.mksoftware101.common.ui_components.input.username

interface UserNameCallback {
    /**
     * Called when userName has changed.
     *
     * [userName] can be null, means invalid
     */
    fun onUserNameChanged(userName: String?)
}