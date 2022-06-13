package mk.software101.features.ui.login

import mk.software101.features.domain.ValidationResult

sealed class LoginPartialState {
    object Init : LoginPartialState()
    data class ValidationFailed(val validationResult: ValidationResult) : LoginPartialState()
    object LoadingVisible : LoginPartialState()
    object LoginSucceed: LoginPartialState()
    object LoginFailed: LoginPartialState()
    object SignupClicked: LoginPartialState()
    object EmailTextChanged: LoginPartialState()
    object PasswordTextChanged: LoginPartialState()
}
