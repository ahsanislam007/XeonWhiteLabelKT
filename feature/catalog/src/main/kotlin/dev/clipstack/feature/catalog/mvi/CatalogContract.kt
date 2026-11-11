package dev.clipstack.feature.catalog.mvi

import dev.clipstack.domain.model.Cart
import dev.clipstack.domain.model.ServiceItem

sealed interface CatalogIntent {
    data object Load : CatalogIntent
    data object Refresh : CatalogIntent
    data class AddService(val serviceId: String) : CatalogIntent
    data object Checkout : CatalogIntent
}

data class CatalogUiState(
    val isLoading: Boolean = true,
    val services: List<ServiceItem> = emptyList(),
    val cart: Cart = Cart(),
    val errorMessage: String? = null,
    val sourceLabel: String = "",
)

sealed interface CatalogUiEffect {
    data class ShowMessage(val message: String) : CatalogUiEffect
    data object NavigateCheckout : CatalogUiEffect
}
