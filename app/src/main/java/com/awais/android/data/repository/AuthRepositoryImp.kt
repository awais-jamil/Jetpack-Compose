package com.awais.android.data.repository

import com.awais.android.core.Constants.ERROR_MESSAGE
import com.awais.android.domain.repository.AuthRepository
import com.awais.android.utils.Response
import com.awais.firebase_service.FirebaseAuthService
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AuthRepositoryImp : AuthRepository {
    private val authService = FirebaseAuthService()
    override suspend fun signIn(email: String, password: String): Flow<Response<Boolean>> {
        
        return callbackFlow {
            authService.signIn(email, password) { result, error ->
                if (result) {
                    trySendBlocking(Response.Success(true))
                } else {
                    trySendBlocking(Response.Error(error ?: ERROR_MESSAGE))
                }
            }
            awaitClose()
        }
    }
    
    override suspend fun signUp(email: String, password: String): Flow<Response<Boolean>> {
        TODO("Not yet implemented")
    }
    
    override suspend fun signOut(): Flow<Response<Boolean>> {
        authService.signOut()
        return callbackFlow {
            trySendBlocking(Response.Success(true))
            awaitClose()
        }
    }
    
    override suspend fun getCurrentUser(): Flow<Response<FirebaseUser?>> {
        return callbackFlow {
            val user = authService.getCurrentUser()
            if (user != null) {
                trySendBlocking(Response.Success(user))
            } else {
                trySendBlocking(Response.Error("User not found"))
            }
            awaitClose()
        }
    }
    
}