package mk.software101.features.domain

import mk.software101.features.models.EmailValidationFailedReason
import mk.software101.features.models.PasswordValidationFailedReason

data class SignupInputValidationResult(
    val success: Boolean,
    val isPasswordsTheSame: Boolean?,
    val emailFailedReasons: Set<EmailValidationFailedReason>? = null,
    val passwordFailedReasons: Set<PasswordValidationFailedReason>? = null,
    val repeatPasswordFailedReasons: Set<PasswordValidationFailedReason>? = null
)

class ValidateSignupInputsUseCase(private val validationHelper: ValidationHelper) {
    fun run(email: String, password: String, repeatPassword: String): SignupInputValidationResult {
        val emailValidationFailedReasons = mutableSetOf<EmailValidationFailedReason>().apply {
            validationHelper.validateEmail(email)?.let { add(it) }
        }
        val passwordValidationFailedReasons = mutableSetOf<PasswordValidationFailedReason>().apply {
            validationHelper.validatePassword(password)?.let { add(it) }
        }
        val repeatPasswordValidationFailedReasons =
            mutableSetOf<PasswordValidationFailedReason>().apply {
                validationHelper.validatePassword(repeatPassword)
                    ?.let { add(it) }
            }

        val hasNotEmailFailedReasons = emailValidationFailedReasons.isEmpty()
        val hasNotPasswordFailedReason = passwordValidationFailedReasons.isEmpty()
        val hasNotRepeatPasswordFailedReason = repeatPasswordValidationFailedReasons.isEmpty()

        val isPasswordsTheSame: Boolean? =
            if (hasNotPasswordFailedReason && hasNotRepeatPasswordFailedReason) {
                password == repeatPassword
            } else {
                null
            }

        val validationSuccess =
            hasNotEmailFailedReasons && hasNotPasswordFailedReason && hasNotRepeatPasswordFailedReason && isPasswordsTheSame ?: false

        return SignupInputValidationResult(
            validationSuccess,
            isPasswordsTheSame,
            emailValidationFailedReasons,
            passwordValidationFailedReasons,
            repeatPasswordValidationFailedReasons
        )
    }
}