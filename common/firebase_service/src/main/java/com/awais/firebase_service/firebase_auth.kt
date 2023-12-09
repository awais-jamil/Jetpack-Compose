package com.awais.firebase_service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthService {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
    
    fun signUp(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, null) // Successful sign-up
                } else {
                    onComplete(false, task.exception?.message ?: "Sign-up failed")
                }
            }
    }
    
    fun signIn(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, null) // Successful sign-in
                } else {
                    onComplete(false, task.exception?.message ?: "Sign-in failed")
                }
            }
    }
    
    fun signOut() {
        auth.signOut()
    }
}
