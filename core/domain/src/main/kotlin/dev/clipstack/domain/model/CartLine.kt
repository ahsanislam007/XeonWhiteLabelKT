package dev.clipstack.domain.model

data class CartLine(
    val service: ServiceItem,
    val quantity: Int,
) {
    val lineTotalCents: Int get() = service.priceCents * quantity
}

data class Cart(
    val lines: List<CartLine> = emptyList(),
) {
    val totalCents: Int get() = lines.sumOf { it.lineTotalCents }
    val isEmpty: Boolean get() = lines.isEmpty()
}
