package com.awais.firebase_service

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query

class FirestoreService<T : Any>(
    private val firestore: FirebaseFirestore,
    private val collectionPath: String,
    private val clazz: Class<T>,
) {
    fun addDocument(data: T, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(collectionPath)
            .add(data)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }
    
    fun updateDocument(
        documentId: String,
        data: T,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        firestore.collection(collectionPath)
            .document(documentId)
            .set(data)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }
    
    fun deleteDocument(documentId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(collectionPath)
            .document(documentId)
            .delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }
    
    fun getDocument(documentId: String, onSuccess: (T?) -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(collectionPath)
            .document(documentId)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val documentSnapshot = task.result
                    val data = documentSnapshot?.toObject(clazz)
                    onSuccess(data)
                } else {
                    onFailure(task.exception ?: Exception("Document not found"))
                }
            }
    }
    
    fun addRealtimeUpdates(
        query: Query,
        onResult: (List<T>) -> Unit,
        onFailure: (Exception) -> Unit,
    ): ListenerRegistration {
        return query.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                onFailure(exception)
            } else {
                val items = snapshot?.documents?.mapNotNull { it.toObject(clazz) }
                onResult(items ?: emptyList())
            }
        }
    }
}

