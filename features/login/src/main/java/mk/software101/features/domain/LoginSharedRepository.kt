package mk.software101.features.domain

import mk.software101.features.models.LoginSharedData

/**
 * Shared repository between login and signup use cases
 */
interface LoginSharedRepository {
    /**
     * Sign up new user. After that, user will be available through ParseUser.getCurrentUser()
     */
    suspend fun signUp(data: LoginSharedData)

    /**
     * Login user. After that, user be available through ParseUser.getCurrentUser()
     */
    suspend fun login(data: LoginSharedData)
}