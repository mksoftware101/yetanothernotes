package mk.software101.features.ui.signup.states

sealed class UiState {
    object IncorrectValuesInFields : UiState()
    object SignUpSucceeded: UiState()
}
