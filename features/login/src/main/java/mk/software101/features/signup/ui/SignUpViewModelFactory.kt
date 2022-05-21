package mk.software101.features.signup.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mk.software101.features.signup.data.SignUpRepositoryImpl
import mk.software101.features.signup.domain.SignUpUseCase
import java.lang.IllegalArgumentException

class SignUpViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            val repository = SignUpRepositoryImpl()
            val signUpUseCase = SignUpUseCase(repository)
            return SignupViewModel(signUpUseCase) as T
        } else throw IllegalArgumentException("modelClass is not derived from ViewModel")
    }
}