package dev.clipstack.database

import app.cash.sqldelight.db.SqlDriver

class ServiceCacheLocalDataSource(
    driver: SqlDriver,
) {
    private val queries = ClipStackDatabase(driver).serviceCacheQueries

    fun readAll(): List<CachedService> =
        queries.selectAll().executeAsList().map {
            CachedService(
                id = it.id,
                name = it.name,
                description = it.description,
                priceCents = it.priceCents.toInt(),
                durationMinutes = it.durationMinutes.toInt(),
                category = it.category,
            )
        }

    fun replaceAll(items: List<CachedService>) {
        queries.transaction {
            queries.deleteAll()
            items.forEach { item ->
                queries.insert(
                    id = item.id,
                    name = item.name,
                    description = item.description,
                    priceCents = item.priceCents.toLong(),
                    durationMinutes = item.durationMinutes.toLong(),
                    category = item.category,
                )
            }
        }
    }
}

data class CachedService(
    val id: String,
    val name: String,
    val description: String,
    val priceCents: Int,
    val durationMinutes: Int,
    val category: String,
)
