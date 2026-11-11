package dev.clipstack.apex.di

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dev.clipstack.analytics.Analytics
import dev.clipstack.analytics.LoggingAnalytics
import dev.clipstack.data.common.InMemoryCartRepository
import dev.clipstack.data.common.theme.BundledThemes
import dev.clipstack.data.common.theme.HybridThemeRepository
import dev.clipstack.data.rest.api.MockRestCatalogApi
import dev.clipstack.data.rest.repository.RestCatalogRepository
import dev.clipstack.data.rest.theme.MockRestThemeApi
import dev.clipstack.database.AppDatabase
import dev.clipstack.database.ServiceCacheLocalDataSource
import dev.clipstack.domain.repository.CartRepository
import dev.clipstack.domain.repository.CatalogRepository
import dev.clipstack.domain.repository.ThemeRepository
import dev.clipstack.feature.catalog.di.catalogFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val apexDataModule = module {
    single {
        AndroidSqliteDriver(
            schema = AppDatabase.Schema,
            context = androidContext(),
            name = "apex_cache.db",
        )
    }
    single { ServiceCacheLocalDataSource(get()) }
    single { MockRestCatalogApi() }
    single { MockRestThemeApi() }
    single<CatalogRepository> { RestCatalogRepository(api = get(), cache = get()) }
    single<CartRepository> { InMemoryCartRepository(get()) }
    single<ThemeRepository> {
        val api: MockRestThemeApi = get()
        HybridThemeRepository(
            localDefault = BundledThemes.apexLocal,
            remoteFetch = { api.fetchTheme("apex") },
        )
    }
    single<Analytics> { LoggingAnalytics() }
}

val apexModules = listOf(
    apexDataModule,
    catalogFeatureModule(
        sourceLabel = "Data: REST + SQLDelight cache",
        localThemeSeed = BundledThemes.apexLocal,
    ),
)
