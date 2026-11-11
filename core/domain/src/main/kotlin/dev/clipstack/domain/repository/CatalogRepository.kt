package dev.clipstack.domain.repository

import dev.clipstack.domain.model.ServiceItem

/**
 * Port (SOLID — Dependency Inversion).
 * Brand apps bind different adapters:
 * - Apex → REST + SQLDelight cache
 * - Bloom → Firebase-style remote source
 */
interface CatalogRepository {
    suspend fun getServices(forceRefresh: Boolean = false): List<ServiceItem>
}
