package dev.clipstack.domain

import dev.clipstack.domain.model.Cart
import dev.clipstack.domain.model.CartLine
import dev.clipstack.domain.model.ServiceCategory
import dev.clipstack.domain.model.ServiceItem
import org.junit.Assert.assertEquals
import org.junit.Test

class CartTest {
    @Test
    fun totalSumsLinePrices() {
        val cut = ServiceItem("1", "Fade", "", 2500, 30, ServiceCategory.HAIRCUT)
        val beard = ServiceItem("2", "Beard", "", 1500, 15, ServiceCategory.BEARD)
        val cart = Cart(
            listOf(
                CartLine(cut, 1),
                CartLine(beard, 2),
            ),
        )
        assertEquals(5500, cart.totalCents)
    }
}
