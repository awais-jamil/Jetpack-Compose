package com.awais.android.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.awais.android.data.repository.AuthRepositoryImp
import com.awais.android.domain.usecases.auth.GetCurrentUserUserCase
import com.awais.android.domain.usecases.auth.LoginUserCase
import com.awais.android.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUserCase = LoginUserCase(AuthRepositoryImp()),
    private val currentUserUserCase: GetCurrentUserUserCase = GetCurrentUserUserCase(
        AuthRepositoryImp()
    ),
) : ViewModel() {
    
    val loading = MutableStateFlow(false)
    val error = MutableStateFlow("")
    val isLoggedIn = MutableStateFlow(false)
    val isAuthenticationLoaded = MutableStateFlow(false)
    
    fun login(email: String, password: String) {
        loading.value = true
        viewModelScope.launch {
            loginUseCase.invoke(email, password).collect { result ->
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
            currentUserUserCase.invoke().collect { result ->
                when (result) {
                    is Response.Success -> {
                        loading.value = false
                        isLoggedIn.value = true
                        isAuthenticationLoaded.value = true
                    }
                    
                    is Response.Error -> {
                        loading.value = false
                        error.value = result.message
                        isAuthenticationLoaded.value = true
                    }
                    
                    is Response.Loading -> {
                        loading.value = true
                    }
                }
            }
        }
    }
}