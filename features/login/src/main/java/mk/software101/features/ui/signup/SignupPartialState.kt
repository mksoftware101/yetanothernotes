package mk.software101.features.ui.signup

import mk.software101.features.domain.ValidationResult
import mk.software101.features.ui.base.BasePartialState

sealed class SignupPartialState : BasePartialState {
    object Init : SignupPartialState()
    data class ValidationFailed(val result: ValidationResult) : SignupPartialState()
    object LoadingVisible : SignupPartialState()
    object SignupSucceed : SignupPartialState()
    object SignupFailed : SignupPartialState()
    object EmailTextChanged : SignupPartialState()
    object PasswordTextChanged : SignupPartialState()
    object RepeatPasswordChanged : SignupPartialState()
}