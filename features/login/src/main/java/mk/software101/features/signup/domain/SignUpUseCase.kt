package mk.software101.features.signup.domain

import mk.software101.features.signup.models.SignUpData

class SignUpUseCase(private val signUpRepository: SignUpRepository) {
    suspend fun run(data: SignUpData) {
        signUpRepository.signUp(data)
    }
}