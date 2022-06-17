package mk.software101.features.ui.signup

import mk.software101.features.models.EmailValidationFailedReason
import mk.software101.features.models.PasswordValidationFailedReason
import mk.software101.features.ui.base.BaseState

data class SignupState(
    val isLoading: Boolean,
    val emailValidationFailedReason: Set<EmailValidationFailedReason>?,
    val passwordValidationFailedReason: Set<PasswordValidationFailedReason>?,
    val repeatPasswordValidationFailedReason: Set<PasswordValidationFailedReason>?,
    val isPasswordsTheSame: Boolean?,
    val isSignupSucceed: Boolean,
    val isSignupFailure: Boolean
) : BaseState {
    companion object {
        fun initialize() = SignupState(
            isLoading = false,
            emailValidationFailedReason = null,
            passwordValidationFailedReason = null,
            repeatPasswordValidationFailedReason = null,
            isPasswordsTheSame = null,
            isSignupSucceed = false,
            isSignupFailure = false
        )

        fun of(
            isLoading: Boolean,
            emailValidationFailedReason: Set<EmailValidationFailedReason>?,
            passwordValidationFailedReason: Set<PasswordValidationFailedReason>?,
            repeatPasswordValidationFailedReason: Set<PasswordValidationFailedReason>?,
            isPasswordsTheSame: Boolean?,
            isSignupSucceed: Boolean,
            isSignupFailure: Boolean
        ) = SignupState(
            isLoading,
            emailValidationFailedReason,
            passwordValidationFailedReason,
            repeatPasswordValidationFailedReason,
            isPasswordsTheSame,
            isSignupSucceed,
            isSignupFailure
        )
    }
}