package com.awais.android.features.auth.domain.usecases

import com.awais.android.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class StoreUserUC @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        email: String,
        userId: String,
        firstName: String,
        lastName: String,
    ) =
        authRepository.storeUserInfo(email, userId, firstName, lastName)
}