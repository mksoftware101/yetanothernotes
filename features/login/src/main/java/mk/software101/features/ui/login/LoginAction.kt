package mk.software101.features.ui.login

import mk.software101.features.common.ViewModelAction
import mk.software101.features.models.LoginSharedData

sealed class LoginAction : ViewModelAction {
    object SetIdle : LoginAction()
    data class ValidateCredentials(val data: LoginSharedData) : LoginAction()
    data class DoLogin(val data: LoginSharedData) : LoginAction()
    object RecoverPassword : LoginAction()
}
