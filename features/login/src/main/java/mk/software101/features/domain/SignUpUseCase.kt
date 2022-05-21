package mk.software101.features.domain

import mk.software101.features.models.LoginSharedData

class SignUpUseCase(private val signUpRepository: LoginSharedRepository) {
    suspend fun run(data: LoginSharedData) {
        signUpRepository.signUp(data)
    }
}