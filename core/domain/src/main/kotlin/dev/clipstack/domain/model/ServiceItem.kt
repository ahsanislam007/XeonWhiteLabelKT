package dev.clipstack.domain.model

/**
 * Pure domain model — no Android, no network, no DB types.
 * Both brands map their wire formats into this shape.
 */
data class ServiceItem(
    val id: String,
    val name: String,
    val description: String,
    val priceCents: Int,
    val durationMinutes: Int,
    val category: ServiceCategory,
)

enum class ServiceCategory {
    HAIRCUT,
    BEARD,
    COLOR,
    STYLING,
    OTHER,
}
