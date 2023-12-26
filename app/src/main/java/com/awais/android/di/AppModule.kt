package com.awais.android.di

import com.awais.android.features.auth.data.repository.AuthRepositoryImp
import com.awais.android.features.auth.domain.repository.AuthRepository
import com.awais.android.features.friends.data.repository.FriendsRepositoryImp
import com.awais.android.features.friends.domains.repository.FriendsRepository
import com.awais.android.features.news.data.repository.NewsRepositoryImp
import com.awais.android.features.news.domains.repository.NewsRepository
import com.awais.firebase_service.FirebaseAuthService
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideFirebaseStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
    
    @Provides
    @Singleton
    fun provideFirebaseAuthService(): FirebaseAuthService {
        return FirebaseAuthService()
    }
    
    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuthService: FirebaseAuthService,
        firestore: FirebaseFirestore,
    ): AuthRepository {
        return AuthRepositoryImp(firebaseAuthService, firestore)
    }
    
    @Provides
    @Singleton
    fun provideFriendsRepository(
        firestore: FirebaseFirestore,
    ): FriendsRepository {
        return FriendsRepositoryImp(firestore)
    }
    
    @Provides
    @Singleton
    fun provideNewsRepository(
    ): NewsRepository {
        return NewsRepositoryImp()
    }
}
