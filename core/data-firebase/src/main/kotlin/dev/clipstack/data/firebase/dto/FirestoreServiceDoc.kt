package dev.clipstack.data.firebase.dto

/**
 * Mimics a Firestore document shape (stringly-typed fields).
 * Real SDK would use DocumentSnapshot — mapper stays the boundary.
 */
data class FirestoreServiceDoc(
    val docId: String,
    val fields: Map<String, String>,
)
