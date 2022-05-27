package mk.software101.features.ui.login.states

sealed class LoginUiState {
    object EmptyEmailAddress: LoginUiState()
    object EmptyPasswordField : LoginUiState()
    object SignupClickedUiState : LoginUiState()
    object LoginSucceeded : LoginUiState()
    object LoginFailed : LoginUiState()
}
