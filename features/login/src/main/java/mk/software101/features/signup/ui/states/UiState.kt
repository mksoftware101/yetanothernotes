package mk.software101.features.signup.ui.states

sealed class UiState {
    object InvalidEmail : UiState()
    object InvalidOrNotSamePassword : UiState()
}
