package mk.software101.features.domain

import com.mksoftware101.core.validator.EmailValidator
import com.mksoftware101.core.validator.PasswordValidator
import mk.software101.features.models.EmailValidationFailedReason
import mk.software101.features.models.LoginSharedData
import mk.software101.features.models.PasswordValidationFailedReason
import mk.software101.features.models.ValidationResult

class ValidateCredentialsUseCase(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) {
    fun run(data: LoginSharedData): ValidationResult {
        val emailValidationFailedReasons = mutableSetOf<EmailValidationFailedReason>()
        val passwordValidationFailedReasons = mutableSetOf<PasswordValidationFailedReason>()
        val invalidEmailReason =
            validateEmail(data.email)?.let { emailValidationFailedReasons.add(it) }
        val invalidPasswordReason =
            validatePassword(data.password)?.let { passwordValidationFailedReasons.add(it) }
        val validationSuccess = invalidEmailReason == null && invalidPasswordReason == null
        return ValidationResult(
            validationSuccess,
            emailValidationFailedReasons,
            passwordValidationFailedReasons
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