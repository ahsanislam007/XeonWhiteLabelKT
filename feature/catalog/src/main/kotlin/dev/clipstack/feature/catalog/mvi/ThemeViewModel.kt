package dev.clipstack.feature.catalog.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.clipstack.domain.model.ThemeConfig
import dev.clipstack.domain.usecase.ObserveThemeUseCase
import dev.clipstack.domain.usecase.RefreshThemeUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(
    observeTheme: ObserveThemeUseCase,
    private val refreshTheme: RefreshThemeUseCase,
    localSeed: ThemeConfig,
) : ViewModel() {

    val theme: StateFlow<ThemeConfig> = observeTheme()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = localSeed,
        )

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch { refreshTheme() }
    }
}
