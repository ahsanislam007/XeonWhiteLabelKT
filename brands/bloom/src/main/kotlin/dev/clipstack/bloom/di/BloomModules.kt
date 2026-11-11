package dev.clipstack.bloom.di

import dev.clipstack.analytics.Analytics
import dev.clipstack.analytics.LoggingAnalytics
import dev.clipstack.data.common.InMemoryCartRepository
import dev.clipstack.data.firebase.FakeFirestoreCatalogApi
import dev.clipstack.data.firebase.repository.FirebaseCatalogRepository
import dev.clipstack.domain.repository.CartRepository
import dev.clipstack.domain.repository.CatalogRepository
import dev.clipstack.feature.catalog.di.catalogFeatureModule
import org.koin.dsl.module

private val bloomDataModule = module {
    single { FakeFirestoreCatalogApi() }
    single<CatalogRepository> { FirebaseCatalogRepository(api = get()) }
    single<CartRepository> { InMemoryCartRepository(get()) }
    single<Analytics> { LoggingAnalytics() }
}

val bloomModules = listOf(
    bloomDataModule,
    catalogFeatureModule(sourceLabel = "Data: Firebase-style (fake Firestore)"),
)
