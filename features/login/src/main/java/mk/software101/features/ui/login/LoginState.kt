package mk.software101.features.ui.login

import mk.software101.features.domain.ValidationFailedReason

data class LoginState private constructor(
    val isLoading: Boolean,
    val isLoginSucceed: Boolean,
    val isLoginFailure: Boolean,
    val validationFailedReasons: Set<ValidationFailedReason>?,
    val isSignupClicked: Boolean
) {
    companion object {
        fun of(
            isLoading: Boolean,
            isLoginSucceed: Boolean,
            isLoginFailure: Boolean,
            validationFailedReasons: Set<ValidationFailedReason>?,
            isSignupClicked: Boolean
        ) = LoginState(isLoading, isLoginSucceed, isLoginFailure, validationFailedReasons, isSignupClicked)

        fun initialize() = LoginState.of(
            isLoading = false,
            isLoginSucceed = false,
            isLoginFailure = false,
            validationFailedReasons = null,
            isSignupClicked = false
        )
    }
}