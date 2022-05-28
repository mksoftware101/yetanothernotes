package mk.software101.features.ui.login

import mk.software101.features.common.ViewIntent
import mk.software101.features.models.LoginSharedData

sealed class LoginIntent : ViewIntent {
    object Idle : LoginIntent()
    data class LogIn(val data: LoginSharedData) : LoginIntent()
    object ForgotPassword : LoginIntent()
}