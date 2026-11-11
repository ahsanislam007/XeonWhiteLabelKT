package dev.clipstack.feature.catalog.di

import dev.clipstack.domain.usecase.AddToCartUseCase
import dev.clipstack.domain.usecase.ClearCartUseCase
import dev.clipstack.domain.usecase.GetServicesUseCase
import dev.clipstack.domain.usecase.ObserveCartUseCase
import dev.clipstack.feature.catalog.mvi.CatalogViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Shared feature bindings. Brand modules still bind CatalogRepository + Analytics.
 */
fun catalogFeatureModule(sourceLabel: String): Module = module {
    factory { GetServicesUseCase(get()) }
    factory { ObserveCartUseCase(get()) }
    factory { AddToCartUseCase(get()) }
    factory { ClearCartUseCase(get()) }
    viewModel {
        CatalogViewModel(
            getServices = get(),
            observeCart = get(),
            addToCart = get(),
            analytics = get(),
            sourceLabel = sourceLabel,
        )
    }
}
