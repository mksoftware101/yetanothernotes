package mk.software101.features.ui.signup

import mk.software101.features.domain.EmailValidationFailedReason
import mk.software101.features.domain.PasswordValidationFailedReason
import mk.software101.features.ui.base.BaseState

data class SignupState(
    val isLoading: Boolean,
    val emailValidationFailedReason: Set<EmailValidationFailedReason>?,
    val passwordValidationFailedReason: Set<PasswordValidationFailedReason>?,
    val repeatPasswordValidationFailedReason: Set<PasswordValidationFailedReason>?,
    val isPasswordsSame: Boolean,
    val isSignupSucceed: Boolean,
    val isSignupFailure: Boolean
) : BaseState {
    companion object {
        fun initialize() = SignupState(
            isLoading = false,
            emailValidationFailedReason = null,
            passwordValidationFailedReason = null,
            repeatPasswordValidationFailedReason = null,
            isPasswordsSame = true,
            isSignupSucceed = false,
            isSignupFailure = false
        )

        fun of(
            isLoading: Boolean,
            emailValidationFailedReason: Set<EmailValidationFailedReason>?,
            passwordValidationFailedReason: Set<PasswordValidationFailedReason>?,
            repeatPasswordValidationFailedReason: Set<PasswordValidationFailedReason>?,
            isPasswordsSame: Boolean,
            isSignupSucceed: Boolean,
            isSignupFailure: Boolean
        ) = SignupState(
            isLoading,
            emailValidationFailedReason,
            passwordValidationFailedReason,
            repeatPasswordValidationFailedReason,
            isPasswordsSame,
            isSignupSucceed,
            isSignupFailure
        )
    }
}