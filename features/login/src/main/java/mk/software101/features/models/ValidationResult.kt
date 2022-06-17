package mk.software101.features.models

enum class EmailValidationFailedReason {
    EMPTY_EMAIL, INVALID_EMAIL
}

enum class PasswordValidationFailedReason {
    EMPTY_PASSWORD, INVALID_PASSWORD
}

data class ValidationResult(
    val success: Boolean,
    val emailFailedReasons: Set<EmailValidationFailedReason>? = null,
    val passwordFailedReasons: Set<PasswordValidationFailedReason>? = null
)