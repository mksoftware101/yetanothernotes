package mk.software101.features.signup.data

import com.parse.ParseUser
import com.parse.coroutines.suspendSignUp
import mk.software101.features.signup.domain.SignUpRepository
import mk.software101.features.signup.models.SignUpData

class SignUpRepositoryImpl : SignUpRepository {
    override suspend fun signUp(data: SignUpData) {
        ParseUser().apply {
            username = data.email
            email = data.email
            setPassword(data.password)
        }.also { it.suspendSignUp() }
    }
}