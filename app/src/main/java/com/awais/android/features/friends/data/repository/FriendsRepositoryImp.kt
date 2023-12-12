package com.awais.android.features.friends.data.repository

import com.awais.android.features.auth.domain.models.User
import com.awais.android.features.friends.domains.repository.FriendsRepository
import com.awais.android.utils.Response
import com.awais.firebase_service.FirestoreService
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FriendsRepositoryImp @Inject constructor(
    private val firestore: FirebaseFirestore,
) :
    FriendsRepository {
    override suspend fun getFriends(userId: String): Flow<Response<List<User>>> {
        val firestoreService = FirestoreService<User>(firestore, "Users", User::class.java)
        return callbackFlow {
            firestoreService.getDocuments(
                query = firestore.collection("Users")
                    .whereNotEqualTo("userId", userId),
                onSuccess = { users ->
                    trySendBlocking(Response.Success(users))
                },
                onFailure = { error ->
                    trySendBlocking(Response.Error(error.message ?: "Error occurred"))
                },
            )
            awaitClose()
        }
    }
}