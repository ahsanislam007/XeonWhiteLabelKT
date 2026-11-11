package dev.clipstack.data.rest.repository

import dev.clipstack.data.rest.api.MockRestCatalogApi
import dev.clipstack.data.rest.mapper.RestServiceMapper
import dev.clipstack.database.ServiceCacheLocalDataSource
import dev.clipstack.domain.model.ServiceItem
import dev.clipstack.domain.repository.CatalogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Apex brand adapter: REST (mock) + SQLDelight cache.
 * Offline-friendly: returns cache if network fails and cache is warm.
 */
class RestCatalogRepository(
    private val api: MockRestCatalogApi,
    private val cache: ServiceCacheLocalDataSource,
) : CatalogRepository {

    override suspend fun getServices(forceRefresh: Boolean): List<ServiceItem> =
        withContext(Dispatchers.IO) {
            val cached = cache.readAll().map(RestServiceMapper::fromCache)
            if (!forceRefresh && cached.isNotEmpty()) {
                // Stale-while-revalidate style: return cache, refresh in background optional.
                // For the template we refresh synchronously only when forced or empty.
                return@withContext cached
            }
            try {
                val remote = api.fetchServices().map(RestServiceMapper::toDomain)
                cache.replaceAll(remote.map(RestServiceMapper::toCache))
                remote
            } catch (t: Throwable) {
                if (cached.isNotEmpty()) cached else throw t
            }
        }
}
