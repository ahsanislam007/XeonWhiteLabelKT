package dev.clipstack.data.firebase

import dev.clipstack.data.firebase.dto.FirestoreServiceDoc
import kotlinx.coroutines.delay

/**
 * Fake Firebase/Firestore remote source.
 *
 * Why fake? So anyone can clone & run without a Google project.
 * Swap this class for a real Firestore SDK call — keep [FirebaseCatalogRepository]
 * and [FirestoreServiceMapper] the same.
 */
class FakeFirestoreCatalogApi {
    suspend fun fetchCollection(collection: String = "services"): List<FirestoreServiceDoc> {
        delay(380)
        check(collection == "services")
        return listOf(
            FirestoreServiceDoc(
                docId = "bloom-cut",
                fields = mapOf(
                    "name" to "Precision Cut",
                    "summary" to "Consultation + wash + cut",
                    "price" to "65.00",
                    "duration" to "50",
                    "tag" to "styling",
                ),
            ),
            FirestoreServiceDoc(
                docId = "bloom-color",
                fields = mapOf(
                    "name" to "Balayage Glow",
                    "summary" to "Hand-painted color, toner included",
                    "price" to "180.00",
                    "duration" to "150",
                    "tag" to "color",
                ),
            ),
            FirestoreServiceDoc(
                docId = "bloom-blow",
                fields = mapOf(
                    "name" to "Blowout",
                    "summary" to "Smooth finish for events",
                    "price" to "45.00",
                    "duration" to "40",
                    "tag" to "styling",
                ),
            ),
            FirestoreServiceDoc(
                docId = "bloom-treat",
                fields = mapOf(
                    "name" to "Keratin Care",
                    "summary" to "Repair mask + styling",
                    "price" to "95.00",
                    "duration" to "75",
                    "tag" to "other",
                ),
            ),
        )
    }
}
