package dev.clipstack.data.common.theme

import dev.clipstack.domain.model.ThemeColorTokens
import dev.clipstack.domain.model.ThemeConfig
import dev.clipstack.domain.model.ThemeShapeTokens
import dev.clipstack.domain.model.ThemeSource
import dev.clipstack.domain.model.ThemeTypeTokens

/**
 * Wire shape for server-driven themes.
 * Keep this stable so a CMS / BFF can push rebrands without an app store release.
 */
data class RemoteThemePayload(
    val brandId: String,
    val displayName: String,
    val isDark: Boolean,
    val colors: Map<String, String>,
    val shapes: Map<String, Int> = emptyMap(),
    val typography: Map<String, String> = emptyMap(),
)

object RemoteThemePayloadMapper {
    fun toDomain(payload: RemoteThemePayload, fallback: ThemeConfig): ThemeConfig {
        val c = fallback.colors
        fun color(key: String, default: String) = payload.colors[key] ?: default
        return ThemeConfig(
            brandId = payload.brandId.ifBlank { fallback.brandId },
            displayName = payload.displayName.ifBlank { fallback.displayName },
            source = ThemeSource.REMOTE,
            isDark = payload.isDark,
            colors = ThemeColorTokens(
                primary = color("primary", c.primary),
                onPrimary = color("onPrimary", c.onPrimary),
                primaryContainer = color("primaryContainer", c.primaryContainer),
                onPrimaryContainer = color("onPrimaryContainer", c.onPrimaryContainer),
                secondary = color("secondary", c.secondary),
                onSecondary = color("onSecondary", c.onSecondary),
                secondaryContainer = color("secondaryContainer", c.secondaryContainer),
                onSecondaryContainer = color("onSecondaryContainer", c.onSecondaryContainer),
                tertiary = color("tertiary", c.tertiary),
                onTertiary = color("onTertiary", c.onTertiary),
                background = color("background", c.background),
                onBackground = color("onBackground", c.onBackground),
                surface = color("surface", c.surface),
                onSurface = color("onSurface", c.onSurface),
                surfaceVariant = color("surfaceVariant", c.surfaceVariant),
                onSurfaceVariant = color("onSurfaceVariant", c.onSurfaceVariant),
                outline = color("outline", c.outline),
                error = color("error", c.error),
                onError = color("onError", c.onError),
            ),
            shapes = ThemeShapeTokens(
                smallDp = payload.shapes["smallDp"] ?: fallback.shapes.smallDp,
                mediumDp = payload.shapes["mediumDp"] ?: fallback.shapes.mediumDp,
                largeDp = payload.shapes["largeDp"] ?: fallback.shapes.largeDp,
            ),
            typography = ThemeTypeTokens(
                displayFamily = payload.typography["displayFamily"] ?: fallback.typography.displayFamily,
                bodyFamily = payload.typography["bodyFamily"] ?: fallback.typography.bodyFamily,
                scale = payload.typography["scale"]?.toFloatOrNull() ?: fallback.typography.scale,
            ),
        )
    }
}
