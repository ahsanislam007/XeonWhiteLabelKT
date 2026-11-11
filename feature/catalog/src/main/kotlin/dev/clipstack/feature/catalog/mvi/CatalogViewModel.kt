package dev.clipstack.feature.catalog.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.clipstack.analytics.Analytics
import dev.clipstack.domain.usecase.AddToCartUseCase
import dev.clipstack.domain.usecase.GetServicesUseCase
import dev.clipstack.domain.usecase.ObserveCartUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val getServices: GetServicesUseCase,
    private val observeCart: ObserveCartUseCase,
    private val addToCart: AddToCartUseCase,
    private val analytics: Analytics,
    private val sourceLabel: String,
) : ViewModel() {

    private val _state = MutableStateFlow(CatalogUiState(sourceLabel = sourceLabel))
    val state: StateFlow<CatalogUiState> = _state.asStateFlow()

    private val _effects = Channel<CatalogUiEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    init {
        onIntent(CatalogIntent.Load)
        viewModelScope.launch {
            observeCart().collect { cart ->
                _state.update { it.copy(cart = cart) }
            }
        }
    }

    fun onIntent(intent: CatalogIntent) {
        when (intent) {
            CatalogIntent.Load -> load(force = false)
            CatalogIntent.Refresh -> load(force = true)
            is CatalogIntent.AddService -> add(intent.serviceId)
            CatalogIntent.Checkout -> checkout()
        }
    }

    private fun load(force: Boolean) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            getServices(forceRefresh = force)
                .onSuccess { services ->
                    analytics.track("catalog_loaded", mapOf("count" to services.size.toString()))
                    _state.update {
                        it.copy(isLoading = false, services = services, errorMessage = null)
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(isLoading = false, errorMessage = error.message ?: "Failed to load")
                    }
                    _effects.send(CatalogUiEffect.ShowMessage("Could not load services"))
                }
        }
    }

    private fun add(serviceId: String) {
        viewModelScope.launch {
            addToCart(serviceId)
            analytics.track("add_to_cart", mapOf("service_id" to serviceId))
            _effects.send(CatalogUiEffect.ShowMessage("Added to booking"))
        }
    }

    private fun checkout() {
        viewModelScope.launch {
            if (_state.value.cart.isEmpty) {
                _effects.send(CatalogUiEffect.ShowMessage("Cart is empty"))
            } else {
                _effects.send(CatalogUiEffect.NavigateCheckout)
            }
        }
    }
}
