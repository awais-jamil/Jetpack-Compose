package com.awais.android.di

import com.awais.android.features.auth.data.repository.AuthRepositoryImp
import com.awais.android.features.auth.domain.repository.AuthRepository
import com.awais.firebase_service.FirebaseAuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    // Define bindings for your dependencies here
    
    @Provides
    @Singleton
    fun provideFirebaseAuthService(): FirebaseAuthService {
        return FirebaseAuthService()
    }
    
    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuthService: FirebaseAuthService): AuthRepository {
        return AuthRepositoryImp(firebaseAuthService)
    }
}
