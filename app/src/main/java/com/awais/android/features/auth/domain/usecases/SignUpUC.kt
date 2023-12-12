package com.awais.android.features.auth.domain.usecases

import com.awais.android.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUC @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) =
        authRepository.signUp(email, password)
}