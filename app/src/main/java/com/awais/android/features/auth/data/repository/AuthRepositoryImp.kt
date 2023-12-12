package com.awais.android.features.auth.data.repository

import com.awais.android.core.Constants.ERROR_MESSAGE
import com.awais.android.features.auth.domain.models.User
import com.awais.android.features.auth.domain.repository.AuthRepository
import com.awais.android.utils.Response
import com.awais.firebase_service.FirebaseAuthService
import com.awais.firebase_service.FirestoreService
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val authService: FirebaseAuthService,
    private val firestore: FirebaseFirestore,
) :
    AuthRepository {
    override suspend fun signIn(email: String, password: String): Flow<Response<String>> {
        
        return callbackFlow {
            authService.signIn(email, password) { result, error ->
                if (!result.isNullOrBlank()) {
                    trySendBlocking(Response.Success(result))
                } else {
                    trySendBlocking(Response.Error(error ?: ERROR_MESSAGE))
                }
            }
            awaitClose()
        }
    }
    
    override suspend fun signUp(email: String, password: String): Flow<Response<String>> {
        return callbackFlow {
            authService.signUp(email, password) { result, error ->
                if (!result.isNullOrBlank()) {
                    trySendBlocking(Response.Success(result))
                } else {
                    trySendBlocking(Response.Error(error ?: ERROR_MESSAGE))
                }
            }
            awaitClose()
        }
    }
    
    override suspend fun storeUserInfo(
        userId: String,
        email: String,
        firstName: String,
        lastName: String,
    ): Flow<Response<Boolean>> {
        val firestoreService = FirestoreService<User>(firestore, "Users", User::class.java)
        return callbackFlow {
            firestoreService.addDocument(
                User(userId, email, firstName, lastName),
                onSuccess = {
                    trySendBlocking(Response.Success(true))
                },
                onFailure = {
                    trySendBlocking(Response.Error(it.message ?: ERROR_MESSAGE))
                },
            )
            awaitClose()
        }
    }
    
    override suspend fun signOut(): Flow<Response<Boolean>> {
        authService.signOut()
        return callbackFlow {
            trySendBlocking(Response.Success(true))
            awaitClose()
        }
    }
    
    override suspend fun getCurrentUser(): Flow<Response<String?>> {
        return callbackFlow {
            val userId = authService.getCurrentUser()
            if (!userId.isNullOrBlank()) {
                trySendBlocking(Response.Success(userId))
            } else {
                trySendBlocking(Response.Error("User not found"))
            }
            awaitClose()
        }
    }
    
}