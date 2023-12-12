package com.awais.android.features.auth.domain.usecases

import com.awais.android.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUC @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) =
        authRepository.signIn(email, password)
}