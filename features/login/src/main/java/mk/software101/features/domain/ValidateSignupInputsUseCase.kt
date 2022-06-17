package mk.software101.features.domain

import com.mksoftware101.core.validator.EmailValidator
import com.mksoftware101.core.validator.PasswordValidator

data class SignupInputValidationResult(
    val success: Boolean,
    val isPasswordsTheSame: Boolean?,
    val emailFailedReasons: Set<EmailValidationFailedReason>? = null,
    val passwordFailedReasons: Set<PasswordValidationFailedReason>? = null,
    val repeatPasswordFailedReasons: Set<PasswordValidationFailedReason>? = null
)

class ValidateSignupInputsUseCase(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) {
    fun run(email: String, password: String, repeatPassword: String): SignupInputValidationResult {
        val emailValidationFailedReasons = mutableSetOf<EmailValidationFailedReason>().apply {
            validateEmail(email)?.let { add(it) }
        }
        val passwordValidationFailedReasons = mutableSetOf<PasswordValidationFailedReason>().apply {
            validatePassword(password)?.let { add(it) }
        }
        val repeatPasswordValidationFailedReasons =
            mutableSetOf<PasswordValidationFailedReason>().apply {
                validatePassword(repeatPassword)?.let { add(it) }
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

    private fun validateEmail(email: String): EmailValidationFailedReason? =
        if (email.isEmpty()) {
            EmailValidationFailedReason.EMPTY_EMAIL
        } else if (!emailValidator.isValid(email)) {
            EmailValidationFailedReason.INVALID_EMAIL
        } else {
            null
        }

    private fun validatePassword(password: String): PasswordValidationFailedReason? =
        if (password.isEmpty()) {
            PasswordValidationFailedReason.EMPTY_PASSWORD
        } else if (!passwordValidator.isValid(password)) {
            PasswordValidationFailedReason.INVALID_PASSWORD
        } else {
            null
        }
}