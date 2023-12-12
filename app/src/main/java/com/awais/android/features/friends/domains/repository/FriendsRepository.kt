package com.awais.android.features.friends.domains.repository

import com.awais.android.features.auth.domain.models.User
import com.awais.android.utils.Response
import kotlinx.coroutines.flow.Flow

interface FriendsRepository {
    suspend fun getFriends(userId: String): Flow<Response<List<User>>>
}