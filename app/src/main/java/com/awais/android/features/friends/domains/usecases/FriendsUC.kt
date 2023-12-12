package com.awais.android.features.friends.domains.usecases

import com.awais.android.features.friends.domains.repository.FriendsRepository
import javax.inject.Inject

class FriendsUC @Inject constructor(private val repository: FriendsRepository) {
    suspend operator fun invoke(userId: String) =
        repository.getFriends(userId)
}