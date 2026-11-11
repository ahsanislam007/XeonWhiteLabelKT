package dev.clipstack.data.common.theme

import dev.clipstack.domain.model.ThemeColorTokens
import dev.clipstack.domain.model.ThemeConfig
import dev.clipstack.domain.model.ThemeShapeTokens
import dev.clipstack.domain.model.ThemeSource
import dev.clipstack.domain.model.ThemeTypeTokens

/**
 * Offline defaults baked into the APK.
 * Remote theme payloads should use the same JSON shape (see sample in docs).
 */
object BundledThemes {
    val apexLocal = ThemeConfig(
        brandId = "apex",
        displayName = "Apex Cuts",
        source = ThemeSource.LOCAL,
        isDark = true,
        colors = ThemeColorTokens(
            primary = "#E8B86D",
            onPrimary = "#1A1208",
            primaryContainer = "#5C4030",
            onPrimaryContainer = "#FFE8C2",
            secondary = "#A1887F",
            onSecondary = "#1A1208",
            secondaryContainer = "#4E342E",
            onSecondaryContainer = "#EFEBE9",
            tertiary = "#80CBC4",
            onTertiary = "#00332E",
            background = "#121212",
            onBackground = "#F5F0E8",
            surface = "#1E1E1E",
            onSurface = "#F5F0E8",
            surfaceVariant = "#2A2A2A",
            onSurfaceVariant = "#BCAAA4",
            outline = "#8D6E63",
        ),
        shapes = ThemeShapeTokens(smallDp = 6, mediumDp = 12, largeDp = 24),
        typography = ThemeTypeTokens(displayFamily = "serif", bodyFamily = "sans", scale = 1.0f),
    )

    val bloomLocal = ThemeConfig(
        brandId = "bloom",
        displayName = "Bloom Studio",
        source = ThemeSource.LOCAL,
        isDark = false,
        colors = ThemeColorTokens(
            primary = "#7A5CFF",
            onPrimary = "#FFFFFF",
            primaryContainer = "#E8DEFF",
            onPrimaryContainer = "#21005E",
            secondary = "#FF8FB8",
            onSecondary = "#3B071F",
            secondaryContainer = "#FFD9E6",
            onSecondaryContainer = "#3B071F",
            tertiary = "#4ECDC4",
            onTertiary = "#003733",
            background = "#FFF7FB",
            onBackground = "#2A2140",
            surface = "#FFFFFF",
            onSurface = "#2A2140",
            surfaceVariant = "#F3ECFF",
            onSurfaceVariant = "#6B6280",
            outline = "#C4B5FD",
        ),
        shapes = ThemeShapeTokens(smallDp = 12, mediumDp = 20, largeDp = 32),
        typography = ThemeTypeTokens(displayFamily = "rounded", bodyFamily = "sans", scale = 1.02f),
    )
}
