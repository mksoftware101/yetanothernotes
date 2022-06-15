package mk.software101.features.domain

import com.mksoftware101.core.validator.EmailValidator
import com.mksoftware101.core.validator.PasswordValidator

data class SignupInputValidationResult(
    val success: Boolean,
    val isPasswordsSame: Boolean,
    val emailFailedReasons: Set<EmailValidationFailedReason>? = null,
    val passwordFailedReasons: Set<PasswordValidationFailedReason>? = null,
    val repeatPasswordFailedReasons: Set<PasswordValidationFailedReason>? = null,
)

class ValidateSignupInputsUseCase(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) {
    fun run(email: String, password: String, repeatPassword: String): SignupInputValidationResult {
        val emailValidationFailedReasons = mutableSetOf<EmailValidationFailedReason>()
        val passwordValidationFailedReasons = mutableSetOf<PasswordValidationFailedReason>()
        val repeatPasswordValidationFailedReasons = mutableSetOf<PasswordValidationFailedReason>()
        val invalidEmailReason =
            validateEmail(email)?.let { emailValidationFailedReasons.add(it) }
        val invalidPasswordReason =
            validatePassword(password)?.let { passwordValidationFailedReasons.add(it) }
        val invalidRepeatPasswordReason =
            validatePassword(repeatPassword)?.let { repeatPasswordValidationFailedReasons.add(it) }
        val isPasswordsTheSame = match(password, repeatPassword)
        val validationSuccess = invalidEmailReason == null && invalidPasswordReason == null &&
                invalidRepeatPasswordReason == null && isPasswordsTheSame
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

    private fun match(password: String, repeatPassword: String) =
        password.isNotBlank() && repeatPassword.isNotBlank() && password == repeatPassword
}