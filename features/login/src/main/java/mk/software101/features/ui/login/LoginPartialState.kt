package mk.software101.features.ui.login

import mk.software101.features.models.ValidationResult
import mk.software101.features.ui.base.BasePartialState

sealed class LoginPartialState : BasePartialState{
    object Init : LoginPartialState()
    data class ValidationFailed(val validationResult: ValidationResult) : LoginPartialState()
    object LoadingVisible : LoginPartialState()
    object LoginSucceed: LoginPartialState()
    object LoginFailed: LoginPartialState()
    object SignupClicked: LoginPartialState()
    object EmailTextChanged: LoginPartialState()
    object PasswordTextChanged: LoginPartialState()
}
