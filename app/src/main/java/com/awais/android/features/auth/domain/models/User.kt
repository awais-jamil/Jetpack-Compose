package com.awais.android.features.auth.domain.models

import com.google.firebase.firestore.PropertyName

data class User(
    val email: String = "",
    @get:PropertyName("userId") // Use PropertyName annotation if the field name in Firestore differs
    val userId: String = "",
    val firstName: String = "",
    val lastName: String = "",
) {
    // Default (no-argument) constructor required for Firestore deserialization
    constructor() : this("", "", "", "")
    
    // Optional: Additional constructors or functions if needed
}

