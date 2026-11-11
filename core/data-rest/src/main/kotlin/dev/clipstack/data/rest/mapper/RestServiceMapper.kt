package dev.clipstack.data.rest.mapper

import dev.clipstack.data.rest.dto.RestServiceDto
import dev.clipstack.database.CachedService
import dev.clipstack.domain.model.ServiceCategory
import dev.clipstack.domain.model.ServiceItem

/**
 * Dedicated mapper (no mapping tables inside DTOs / ViewModels / UI).
 */
object RestServiceMapper {
    fun toDomain(dto: RestServiceDto): ServiceItem =
        ServiceItem(
            id = dto.id,
            name = dto.title,
            description = dto.blurb,
            priceCents = dto.priceCents,
            durationMinutes = dto.mins,
            category = categoryFrom(dto.type),
        )

    fun toCache(item: ServiceItem): CachedService =
        CachedService(
            id = item.id,
            name = item.name,
            description = item.description,
            priceCents = item.priceCents,
            durationMinutes = item.durationMinutes,
            category = item.category.name,
        )

    fun fromCache(cached: CachedService): ServiceItem =
        ServiceItem(
            id = cached.id,
            name = cached.name,
            description = cached.description,
            priceCents = cached.priceCents,
            durationMinutes = cached.durationMinutes,
            category = runCatching { ServiceCategory.valueOf(cached.category) }
                .getOrDefault(ServiceCategory.OTHER),
        )

    private fun categoryFrom(type: String): ServiceCategory =
        when (type.lowercase()) {
            "haircut" -> ServiceCategory.HAIRCUT
            "beard" -> ServiceCategory.BEARD
            "color" -> ServiceCategory.COLOR
            "styling" -> ServiceCategory.STYLING
            else -> ServiceCategory.OTHER
        }
}
