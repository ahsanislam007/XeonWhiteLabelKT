package dev.clipstack.domain.repository

import dev.clipstack.domain.model.Cart
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    val cart: Flow<Cart>
    suspend fun add(serviceId: String)
    suspend fun remove(serviceId: String)
    suspend fun clear()
}
