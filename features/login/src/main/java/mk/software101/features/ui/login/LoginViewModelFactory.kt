package mk.software101.features.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mk.software101.features.data.LoginSharedRepositoryImpl
import mk.software101.features.domain.LoginSharedRepository
import mk.software101.features.domain.LoginUseCase

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                LoginUseCase(
                    LoginSharedRepositoryImpl()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}