package dev.clipstack.ui.theme

import androidx.compose.ui.graphics.Color

object HexColorParser {
    fun parse(hex: String, fallback: Color = Color.Magenta): Color {
        val cleaned = hex.trim().removePrefix("#")
        val value = when (cleaned.length) {
            6 -> cleaned.toLongOrNull(16)?.or(0xFF000000L)
            8 -> cleaned.toLongOrNull(16)
            else -> null
        } ?: return fallback
        return Color(value)
    }
}
