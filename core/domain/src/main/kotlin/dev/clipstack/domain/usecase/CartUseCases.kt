package dev.clipstack.domain.usecase

import dev.clipstack.domain.model.Cart
import dev.clipstack.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class ObserveCartUseCase(
    private val cartRepository: CartRepository,
) {
    operator fun invoke(): Flow<Cart> = cartRepository.cart
}

class AddToCartUseCase(
    private val cartRepository: CartRepository,
) {
    suspend operator fun invoke(serviceId: String) = cartRepository.add(serviceId)
}

class ClearCartUseCase(
    private val cartRepository: CartRepository,
) {
    suspend operator fun invoke() = cartRepository.clear()
}
