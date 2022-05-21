package mk.software101.features.ui.signup.states

sealed class UiState {
    object PasswordsNotSame : UiState()
    object SignUpSucceeded : UiState()
    object SignUpFailed : UiState()
}
