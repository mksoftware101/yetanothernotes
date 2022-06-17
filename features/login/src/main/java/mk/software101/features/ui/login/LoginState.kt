package mk.software101.features.ui.login

import mk.software101.features.models.EmailValidationFailedReason
import mk.software101.features.models.PasswordValidationFailedReason
import mk.software101.features.ui.base.BaseState

data class LoginState private constructor(
    val isLoading: Boolean,
    val isLoginSucceed: Boolean,
    val isLoginFailure: Boolean,
    val emailValidationFailedReasons: Set<EmailValidationFailedReason>?,
    val passwordValidationFailedReasons: Set<PasswordValidationFailedReason>?,
    val isSignupClicked: Boolean
) : BaseState {
    companion object {
        fun of(
            isLoading: Boolean,
            isLoginSucceed: Boolean,
            isLoginFailure: Boolean,
            emailValidationFailedReasons: Set<EmailValidationFailedReason>?,
            passwordValidationFailedReasons: Set<PasswordValidationFailedReason>?,
            isSignupClicked: Boolean
        ) = LoginState(
            isLoading,
            isLoginSucceed,
            isLoginFailure,
            emailValidationFailedReasons,
            passwordValidationFailedReasons,
            isSignupClicked
        )

        fun initialize() = LoginState.of(
            isLoading = false,
            isLoginSucceed = false,
            isLoginFailure = false,
            emailValidationFailedReasons = null,
            passwordValidationFailedReasons = null,
            isSignupClicked = false
        )
    }
}