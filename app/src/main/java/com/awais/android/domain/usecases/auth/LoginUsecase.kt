package com.awais.android.domain.usecases.auth

import com.awais.android.domain.repository.AuthRepository

class LoginUserCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) =
        authRepository.signIn(email, password)
}