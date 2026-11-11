package dev.clipstack.data.rest.theme

import dev.clipstack.data.common.theme.RemoteThemePayload
import kotlinx.coroutines.delay

/**
 * Simulates a theme endpoint on Server A, e.g. GET /v1/brands/apex/theme
 * Slightly different tokens than the local default so you can see remote apply.
 */
class MockRestThemeApi {
    suspend fun fetchTheme(brandId: String = "apex"): RemoteThemePayload {
        delay(500)
        return RemoteThemePayload(
            brandId = brandId,
            displayName = "Apex Cuts",
            isDark = true,
            colors = mapOf(
                "primary" to "#F0C27A",
                "onPrimary" to "#1A1208",
                "primaryContainer" to "#6D4C2F",
                "onPrimaryContainer" to "#FFF0D4",
                "secondary" to "#BCAAA4",
                "onSecondary" to "#1A1208",
                "secondaryContainer" to "#4E342E",
                "onSecondaryContainer" to "#EFEBE9",
                "tertiary" to "#80CBC4",
                "onTertiary" to "#00332E",
                "background" to "#0E0E0E",
                "onBackground" to "#F5F0E8",
                "surface" to "#1A1A1A",
                "onSurface" to "#F5F0E8",
                "surfaceVariant" to "#2E2A26",
                "onSurfaceVariant" to "#D7CCC8",
                "outline" to "#A1887F",
            ),
            shapes = mapOf("smallDp" to 8, "mediumDp" to 14, "largeDp" to 28),
            typography = mapOf("displayFamily" to "serif", "bodyFamily" to "sans", "scale" to "1.0"),
        )
    }
}
