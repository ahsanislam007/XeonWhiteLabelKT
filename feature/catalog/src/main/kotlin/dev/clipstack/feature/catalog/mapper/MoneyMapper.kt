package dev.clipstack.feature.catalog.mapper

import java.util.Locale

object MoneyMapper {
    fun formatCents(cents: Int, currencySymbol: String = "$"): String {
        val dollars = cents / 100.0
        return String.format(Locale.US, "%s%.2f", currencySymbol, dollars)
    }
}
