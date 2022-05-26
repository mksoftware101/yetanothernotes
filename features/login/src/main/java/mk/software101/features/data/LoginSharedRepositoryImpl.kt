package mk.software101.features.data

import com.parse.ParseUser
import com.parse.coroutines.parseLogIn
import com.parse.coroutines.suspendSignUp
import mk.software101.features.domain.LoginSharedRepository
import mk.software101.features.models.LoginSharedData

class LoginSharedRepositoryImpl : LoginSharedRepository {
    override suspend fun signUp(data: LoginSharedData) {
        ParseUser().apply {
            username = data.email
            email = data.email
            setPassword(data.password)
        }.also { it.suspendSignUp() }
    }

    override suspend fun login(data: LoginSharedData) {
        parseLogIn(data.email, data.password)
    }
}