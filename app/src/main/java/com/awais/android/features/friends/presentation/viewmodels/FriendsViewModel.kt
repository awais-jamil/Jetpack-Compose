package com.awais.android.features.friends.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.awais.android.features.auth.domain.models.User
import com.awais.android.features.friends.domains.usecases.FriendsUC
import com.awais.android.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val friendsUC: FriendsUC,
) : ViewModel() {
    val loading = MutableStateFlow(false)
    private val _users = MutableStateFlow<List<User>>(
        emptyList()
    )
    val users = _users.asStateFlow()
    
    fun fetchFriends(userId: String) {
        loading.value = true
        viewModelScope.launch {
            friendsUC.invoke(userId).collect { result ->
                when (result) {
                    is Response.Success -> {
                        _users.tryEmit(result.data)
                        loading.value = false
                    }
                    
                    is Response.Error -> {
                        loading.value = false
                        _users.value = emptyList()
                    }
                    
                    is Response.Loading -> {
                        loading.value = true
                    }
                }
            }
        }
    }
}