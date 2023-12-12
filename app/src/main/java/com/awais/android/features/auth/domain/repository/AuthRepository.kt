package com.awais.android.features.auth.domain.repository

import com.awais.android.utils.Response
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Flow<Response<String>>
    suspend fun signUp(email: String, password: String): Flow<Response<String>>
    suspend fun storeUserInfo(
        email: String,
        userId: String,
        firstName: String,
        lastName: String,
    ): Flow<Response<Boolean>>
    
    suspend fun signOut(): Flow<Response<Boolean>>
    suspend fun getCurrentUser(): Flow<Response<String?>>
}