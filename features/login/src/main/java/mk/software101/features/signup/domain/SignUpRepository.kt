package mk.software101.features.signup.domain

import mk.software101.features.signup.models.SignUpData

interface SignUpRepository {
    suspend fun signUp(data: SignUpData)
}