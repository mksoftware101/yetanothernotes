package mk.software101.features.domain

import com.mksoftware101.core.validator.EmailValidator
import com.mksoftware101.core.validator.PasswordValidator
import mk.software101.features.models.LoginSharedData

enum class ValidationFailedReason {
    EMPTY_EMAIL, INVALID_EMAIL, EMPTY_PASSWORD, INVALID_PASSWORD
}

data class ValidationResult(
    val success: Boolean,
    val failedReasons: Set<ValidationFailedReason>? = null
)

class ValidateCredentialsUseCase(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) {
    fun run(data: LoginSharedData): ValidationResult {
        val validationFailedReasons = mutableSetOf<ValidationFailedReason>()
        val invalidEmailReason = validateEmail(data.email)?.let { validationFailedReasons.add(it) }
        val invalidPasswordReason =
            validatePassword(data.password)?.let { validationFailedReasons.add(it) }
        val validationSuccess = invalidEmailReason == null && invalidPasswordReason == null
        return ValidationResult(validationSuccess, validationFailedReasons)
    }

    private fun validateEmail(email: String): ValidationFailedReason? =
        if (email.isEmpty()) {
            ValidationFailedReason.EMPTY_EMAIL
        } else if (!emailValidator.isValid(email)) {
            ValidationFailedReason.INVALID_EMAIL
        } else {
            null
        }

    private fun validatePassword(password: String): ValidationFailedReason? =
        if (password.isEmpty()) {
            ValidationFailedReason.EMPTY_PASSWORD
        } else if (!passwordValidator.isValid(password)) {
            ValidationFailedReason.INVALID_PASSWORD
        } else {
            null
        }
}