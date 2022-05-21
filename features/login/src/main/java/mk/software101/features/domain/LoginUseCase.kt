package mk.software101.features.domain

import mk.software101.features.models.LoginSharedData

class LoginUseCase(private val repository: LoginSharedRepository) {
    suspend fun run(data: LoginSharedData) {
        repository.login(data)
    }
}