package mk.software101.features.domain

import com.mksoftware101.core.validator.EmailValidator
import com.mksoftware101.core.validator.PasswordValidator
import mk.software101.features.models.EmailValidationFailedReason
import mk.software101.features.models.PasswordValidationFailedReason

class ValidationHelper(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) {
    fun validateEmail(email: String) = if (email.isEmpty()) {
        EmailValidationFailedReason.EMPTY_EMAIL
    } else if (!emailValidator.isValid(email)) {
        EmailValidationFailedReason.INVALID_EMAIL
    } else {
        null
    }

    fun validatePassword(password: String) = if (password.isEmpty()) {
        PasswordValidationFailedReason.EMPTY_PASSWORD
    } else if (!passwordValidator.isValid(password)) {
        PasswordValidationFailedReason.INVALID_PASSWORD
    } else {
        null
    }
}