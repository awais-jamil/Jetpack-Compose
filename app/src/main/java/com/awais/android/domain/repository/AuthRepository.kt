package com.awais.android.domain.repository

import com.awais.android.utils.Response
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Flow<Response<Boolean>>
    suspend fun signUp(email: String, password: String): Flow<Response<Boolean>>
    suspend fun signOut(): Flow<Response<Boolean>>
    suspend fun getCurrentUser(): Flow<Response<FirebaseUser?>>
}