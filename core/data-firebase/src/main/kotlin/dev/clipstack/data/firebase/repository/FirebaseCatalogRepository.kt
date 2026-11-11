package dev.clipstack.data.firebase.repository

import dev.clipstack.data.firebase.FakeFirestoreCatalogApi
import dev.clipstack.data.firebase.mapper.FirestoreServiceMapper
import dev.clipstack.domain.model.ServiceItem
import dev.clipstack.domain.repository.CatalogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Bloom brand adapter: Firebase-style remote (fake Firestore).
 * No SQLDelight here on purpose — shows a different data stack per brand.
 */
class FirebaseCatalogRepository(
    private val api: FakeFirestoreCatalogApi,
) : CatalogRepository {
    override suspend fun getServices(forceRefresh: Boolean): List<ServiceItem> =
        withContext(Dispatchers.IO) {
            api.fetchCollection()
                .mapNotNull(FirestoreServiceMapper::toDomain)
        }
}
