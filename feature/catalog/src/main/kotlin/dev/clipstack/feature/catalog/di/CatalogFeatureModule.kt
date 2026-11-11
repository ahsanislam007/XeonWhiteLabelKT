package dev.clipstack.feature.catalog.di

import dev.clipstack.domain.model.ThemeConfig
import dev.clipstack.domain.usecase.AddToCartUseCase
import dev.clipstack.domain.usecase.ClearCartUseCase
import dev.clipstack.domain.usecase.GetServicesUseCase
import dev.clipstack.domain.usecase.ObserveCartUseCase
import dev.clipstack.domain.usecase.ObserveThemeUseCase
import dev.clipstack.domain.usecase.RefreshThemeUseCase
import dev.clipstack.feature.catalog.mvi.CatalogViewModel
import dev.clipstack.feature.catalog.mvi.ThemeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Shared feature bindings. Brand modules still bind CatalogRepository, ThemeRepository, Analytics.
 */
fun catalogFeatureModule(
    sourceLabel: String,
    localThemeSeed: ThemeConfig,
): Module = module {
    factory { GetServicesUseCase(get()) }
    factory { ObserveCartUseCase(get()) }
    factory { AddToCartUseCase(get()) }
    factory { ClearCartUseCase(get()) }
    factory { ObserveThemeUseCase(get()) }
    factory { RefreshThemeUseCase(get()) }
    viewModel {
        CatalogViewModel(
            getServices = get(),
            observeCart = get(),
            addToCart = get(),
            analytics = get(),
            sourceLabel = sourceLabel,
        )
    }
    viewModel {
        ThemeViewModel(
            observeTheme = get(),
            refreshTheme = get(),
            localSeed = localThemeSeed,
        )
    }
}
