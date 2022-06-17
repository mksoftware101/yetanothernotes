package mk.software101.features.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mksoftware101.core.validator.EmailValidator
import com.mksoftware101.core.validator.PasswordValidator
import mk.software101.features.data.LoginSharedRepositoryImpl
import mk.software101.features.domain.SignUpUseCase
import mk.software101.features.domain.ValidateSignupInputsUseCase
import mk.software101.features.domain.ValidationHelper
import java.lang.IllegalArgumentException

class SignUpViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            val repository = LoginSharedRepositoryImpl()
            val validateSignupInputsUseCase =
                ValidateSignupInputsUseCase(ValidationHelper(EmailValidator(), PasswordValidator()))
            val signUpUseCase = SignUpUseCase(repository)
            return SignupViewModel(validateSignupInputsUseCase, signUpUseCase) as T
        } else throw IllegalArgumentException("modelClass is not derived from ViewModel")
    }
}