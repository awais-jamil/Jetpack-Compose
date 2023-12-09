package com.awais.android.domain.usecases.auth

import com.awais.android.domain.repository.AuthRepository

class GetCurrentUserUserCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke() =
        authRepository.getCurrentUser()
}