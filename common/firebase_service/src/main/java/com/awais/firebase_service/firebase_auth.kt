package com.awais.firebase_service

import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthService {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    
    fun getCurrentUser(): String? {
        return auth.currentUser?.uid
    }
    
    fun signUp(email: String, password: String, onComplete: (String?, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid ?: ""
                    onComplete(uid, null)
                } else {
                    onComplete(null, task.exception?.message ?: "Sign-up failed")
                }
            }
    }
    
    fun signIn(email: String, password: String, onComplete: (String?, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid ?: ""
                    onComplete(uid, null)
                } else {
                    onComplete(null, task.exception?.message ?: "Sign-in failed")
                }
            }
    }
    
    fun signOut() {
        auth.signOut()
    }
}
