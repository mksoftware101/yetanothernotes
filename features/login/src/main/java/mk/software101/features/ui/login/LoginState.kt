package mk.software101.features.ui.login

import mk.software101.features.common.ViewState

sealed class LoginState : ViewState {
    object Idle : LoginState()
    object Loading: LoginState()
    object EmptyEmail : LoginState()
    object InvalidEmail : LoginState()
    object EmptyPassword : LoginState()
    object InvalidPassword : LoginState()
    object LoginSucceeded : LoginState()
    object LoginFailed: LoginState()
}
