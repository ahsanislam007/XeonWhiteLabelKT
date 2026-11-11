package dev.clipstack.data.common

import dev.clipstack.domain.model.Cart
import dev.clipstack.domain.model.CartLine
import dev.clipstack.domain.model.ServiceItem
import dev.clipstack.domain.repository.CartRepository
import dev.clipstack.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Simple cart shared by both brands for the template.
 * Promote to SQLDelight / remote cart when you productize.
 */
class InMemoryCartRepository(
    private val catalogRepository: CatalogRepository,
) : CartRepository {

    private val mutex = Mutex()
    private val _cart = MutableStateFlow(Cart())
    override val cart: Flow<Cart> = _cart.asStateFlow()

    private var catalog: Map<String, ServiceItem> = emptyMap()

    override suspend fun add(serviceId: String) = mutex.withLock {
        ensureCatalog()
        val service = catalog[serviceId] ?: return
        _cart.update { current ->
            val existing = current.lines.find { it.service.id == serviceId }
            val lines = if (existing == null) {
                current.lines + CartLine(service, 1)
            } else {
                current.lines.map {
                    if (it.service.id == serviceId) it.copy(quantity = it.quantity + 1) else it
                }
            }
            Cart(lines)
        }
    }

    override suspend fun remove(serviceId: String) = mutex.withLock {
        _cart.update { current ->
            Cart(current.lines.filterNot { it.service.id == serviceId })
        }
    }

    override suspend fun clear() = mutex.withLock {
        _cart.value = Cart()
    }

    private suspend fun ensureCatalog() {
        if (catalog.isEmpty()) {
            catalog = catalogRepository.getServices().associateBy { it.id }
        }
    }
}
