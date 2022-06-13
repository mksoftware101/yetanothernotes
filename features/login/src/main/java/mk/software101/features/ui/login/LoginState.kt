package mk.software101.features.ui.login

import mk.software101.features.domain.ValidationResult

data class LoginState private constructor(
    val isLoading: Boolean,
    val isLoginSucceed: Boolean,
    val isLoginFailure: Boolean,
    val validationResult: ValidationResult?,
    val isSignupClicked: Boolean
) {
    companion object {
        fun of(
            isLoading: Boolean,
            isLoginSucceed: Boolean,
            isLoginFailure: Boolean,
            validationResult: ValidationResult?,
            isSignupClicked: Boolean
        ) = LoginState(isLoading, isLoginSucceed, isLoginFailure, validationResult, isSignupClicked)

        fun initialize() = LoginState.of(
            isLoading = false,
            isLoginSucceed = false,
            isLoginFailure = false,
            validationResult = null,
            isSignupClicked = false
        )
    }
}