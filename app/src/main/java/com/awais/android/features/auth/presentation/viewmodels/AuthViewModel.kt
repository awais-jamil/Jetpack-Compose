package com.awais.android.features.auth.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.awais.android.features.auth.domain.usecases.CurrentUserUC
import com.awais.android.features.auth.domain.usecases.LoginUC
import com.awais.android.features.auth.domain.usecases.LogoutUC
import com.awais.android.features.auth.domain.usecases.SignUpUC
import com.awais.android.features.auth.domain.usecases.StoreUserUC
import com.awais.android.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUC: LoginUC,
    private val currentUC: CurrentUserUC,
    private val signUpUC: SignUpUC,
    private val storeUserUC: StoreUserUC,
    private val logoutUC: LogoutUC,
) : ViewModel() {
    
    val currentUserId = MutableStateFlow("")
    
    val loading = MutableStateFlow(false)
    val error = MutableStateFlow("")
    val isLoggedIn = MutableStateFlow(false)
    val isAuthenticationLoaded = MutableStateFlow(false)
    
    fun login(email: String, password: String) {
        loading.value = true
        viewModelScope.launch {
            loginUC.invoke(email, password).collect { result ->
                isAuthenticationLoaded.value = true
                when (result) {
                    is Response.Success -> {
                        loading.value = false
                        isLoggedIn.value = true
                        currentUserId.value = result.data
                    }
                    
                    is Response.Error -> {
                        loading.value = false
                        error.value = result.message
                        currentUserId.value = ""
                    }
                    
                    is Response.Loading -> {
                        loading.value = true
                    }
                }
            }
        }
    }
    
    fun logout() {
        viewModelScope.launch {
            logoutUC.invoke().collect { result ->
                isAuthenticationLoaded.value = true
                when (result) {
                    is Response.Success -> {
                        loading.value = false
                        isLoggedIn.value = false
                    }
                    
                    is Response.Error -> {
                        loading.value = false
                        error.value = result.message
                    }
                    
                    is Response.Loading -> {
                    }
                }
            }
        }
    }
    
    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        loading.value = true
        viewModelScope.launch {
            signUpUC.invoke(email, password).collect { result ->
                when (result) {
                    is Response.Success -> {
                        loading.value = false
                        isLoggedIn.value = true
                        isAuthenticationLoaded.value = true
                        currentUserId.value = result.data
                        storeUserInfo(email, result.data, firstName, lastName)
                    }
                    
                    is Response.Error -> {
                        loading.value = false
                        error.value = result.message
                        isAuthenticationLoaded.value = true
                        currentUserId.value = ""
                    }
                    
                    is Response.Loading -> {}
                }
            }
        }
    }
    
    private fun storeUserInfo(email: String, userId: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            storeUserUC.invoke(email, userId, firstName, lastName).collect { result ->
                when (result) {
                    is Response.Success -> {
                        loading.value = false
                        isLoggedIn.value = true
                    }
                    
                    is Response.Error -> {
                        loading.value = false
                        error.value = result.message
                    }
                    
                    is Response.Loading -> {
                        loading.value = true
                    }
                }
            }
        }
    }
    
    fun getCurrentUser() {
        viewModelScope.launch {
            currentUC.invoke().collect { result ->
                isAuthenticationLoaded.value = true
                when (result) {
                    is Response.Success -> {
                        loading.value = false
                        if (result.data != null) {
                            currentUserId.value = result.data
                            isLoggedIn.value = true
                        } else {
                            currentUserId.value = ""
                            isLoggedIn.value = false
                        }
                    }
                    
                    is Response.Error -> {
                        loading.value = false
                        error.value = result.message
                        currentUserId.value = ""
                    }
                    
                    is Response.Loading -> {
                        loading.value = true
                    }
                }
            }
        }
    }
}