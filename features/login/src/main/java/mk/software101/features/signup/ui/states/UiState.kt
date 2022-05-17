package mk.software101.features.signup.ui.states

sealed class UiState {
    object IncorrectValuesInFields : UiState()
    object OpenNotesList : UiState()
}
