package mk.software101.features.domain

import mk.software101.features.models.EmailValidationFailedReason
import mk.software101.features.models.LoginSharedData
import mk.software101.features.models.PasswordValidationFailedReason
import mk.software101.features.models.ValidationResult

class ValidateCredentialsUseCase(
    private val validationHelper: ValidationHelper,
) {
    fun run(data: LoginSharedData): ValidationResult {
        val emailValidationFailedReasons = mutableSetOf<EmailValidationFailedReason>().apply {
            validationHelper.validateEmail(data.email)?.let { add(it) }
        }
        val passwordValidationFailedReasons = mutableSetOf<PasswordValidationFailedReason>().apply {
            validationHelper.validatePassword(data.password)?.let { add(it) }
        }

        val hasNotEmailValidationFailedReason = emailValidationFailedReasons.isEmpty()
        val hasNotPasswordValidationFailedReason = passwordValidationFailedReasons.isEmpty()

        val validationSuccess =
            hasNotEmailValidationFailedReason && hasNotPasswordValidationFailedReason

        return ValidationResult(
            validationSuccess,
            emailValidationFailedReasons,
            passwordValidationFailedReasons
        )
    }
}