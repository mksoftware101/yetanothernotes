package mk.software101.features.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mk.software101.features.data.LoginSharedRepositoryImpl
import mk.software101.features.domain.SignUpUseCase
import java.lang.IllegalArgumentException

class SignUpViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            val repository = LoginSharedRepositoryImpl()
            val signUpUseCase = SignUpUseCase(repository)
            return SignupViewModel(signUpUseCase) as T
        } else throw IllegalArgumentException("modelClass is not derived from ViewModel")
    }
}