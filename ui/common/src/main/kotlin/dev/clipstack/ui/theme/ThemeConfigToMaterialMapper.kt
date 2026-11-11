package dev.clipstack.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.clipstack.domain.model.ThemeConfig

/**
 * Dedicated mapper: domain [ThemeConfig] → Material 3 ColorScheme / Typography / Shapes.
 * Never put this logic in ViewModels or screens.
 */
object ThemeConfigToMaterialMapper {

    fun colorScheme(config: ThemeConfig): ColorScheme {
        val c = config.colors
        val primary = HexColorParser.parse(c.primary)
        val onPrimary = HexColorParser.parse(c.onPrimary)
        val primaryContainer = HexColorParser.parse(c.primaryContainer)
        val onPrimaryContainer = HexColorParser.parse(c.onPrimaryContainer)
        val secondary = HexColorParser.parse(c.secondary)
        val onSecondary = HexColorParser.parse(c.onSecondary)
        val secondaryContainer = HexColorParser.parse(c.secondaryContainer)
        val onSecondaryContainer = HexColorParser.parse(c.onSecondaryContainer)
        val tertiary = HexColorParser.parse(c.tertiary)
        val onTertiary = HexColorParser.parse(c.onTertiary)
        val background = HexColorParser.parse(c.background)
        val onBackground = HexColorParser.parse(c.onBackground)
        val surface = HexColorParser.parse(c.surface)
        val onSurface = HexColorParser.parse(c.onSurface)
        val surfaceVariant = HexColorParser.parse(c.surfaceVariant)
        val onSurfaceVariant = HexColorParser.parse(c.onSurfaceVariant)
        val outline = HexColorParser.parse(c.outline)
        val error = HexColorParser.parse(c.error)
        val onError = HexColorParser.parse(c.onError)

        return if (config.isDark) {
            darkColorScheme(
                primary = primary,
                onPrimary = onPrimary,
                primaryContainer = primaryContainer,
                onPrimaryContainer = onPrimaryContainer,
                secondary = secondary,
                onSecondary = onSecondary,
                secondaryContainer = secondaryContainer,
                onSecondaryContainer = onSecondaryContainer,
                tertiary = tertiary,
                onTertiary = onTertiary,
                background = background,
                onBackground = onBackground,
                surface = surface,
                onSurface = onSurface,
                surfaceVariant = surfaceVariant,
                onSurfaceVariant = onSurfaceVariant,
                outline = outline,
                error = error,
                onError = onError,
            )
        } else {
            lightColorScheme(
                primary = primary,
                onPrimary = onPrimary,
                primaryContainer = primaryContainer,
                onPrimaryContainer = onPrimaryContainer,
                secondary = secondary,
                onSecondary = onSecondary,
                secondaryContainer = secondaryContainer,
                onSecondaryContainer = onSecondaryContainer,
                tertiary = tertiary,
                onTertiary = onTertiary,
                background = background,
                onBackground = onBackground,
                surface = surface,
                onSurface = onSurface,
                surfaceVariant = surfaceVariant,
                onSurfaceVariant = onSurfaceVariant,
                outline = outline,
                error = error,
                onError = onError,
            )
        }
    }

    fun shapes(config: ThemeConfig): Shapes {
        val s = config.shapes
        return Shapes(
            extraSmall = RoundedCornerShape(s.smallDp.dp),
            small = RoundedCornerShape(s.smallDp.dp),
            medium = RoundedCornerShape(s.mediumDp.dp),
            large = RoundedCornerShape(s.largeDp.dp),
            extraLarge = RoundedCornerShape((s.largeDp + 8).dp),
        )
    }

    fun typography(config: ThemeConfig): Typography {
        val scale = config.typography.scale
        val display = familyOf(config.typography.displayFamily)
        val body = familyOf(config.typography.bodyFamily)
        return Typography(
            displayLarge = TextStyle(
                fontFamily = display,
                fontWeight = FontWeight.Bold,
                fontSize = (57 * scale).sp,
                lineHeight = (64 * scale).sp,
            ),
            headlineMedium = TextStyle(
                fontFamily = display,
                fontWeight = FontWeight.SemiBold,
                fontSize = (28 * scale).sp,
                lineHeight = (36 * scale).sp,
            ),
            titleLarge = TextStyle(
                fontFamily = display,
                fontWeight = FontWeight.SemiBold,
                fontSize = (22 * scale).sp,
                lineHeight = (28 * scale).sp,
            ),
            titleMedium = TextStyle(
                fontFamily = display,
                fontWeight = FontWeight.SemiBold,
                fontSize = (16 * scale).sp,
                lineHeight = (24 * scale).sp,
            ),
            titleSmall = TextStyle(
                fontFamily = display,
                fontWeight = FontWeight.Medium,
                fontSize = (14 * scale).sp,
                lineHeight = (20 * scale).sp,
            ),
            bodyLarge = TextStyle(
                fontFamily = body,
                fontWeight = FontWeight.Normal,
                fontSize = (16 * scale).sp,
                lineHeight = (24 * scale).sp,
            ),
            bodyMedium = TextStyle(
                fontFamily = body,
                fontWeight = FontWeight.Normal,
                fontSize = (14 * scale).sp,
                lineHeight = (20 * scale).sp,
            ),
            labelLarge = TextStyle(
                fontFamily = body,
                fontWeight = FontWeight.Medium,
                fontSize = (14 * scale).sp,
                lineHeight = (20 * scale).sp,
            ),
            labelMedium = TextStyle(
                fontFamily = body,
                fontWeight = FontWeight.Medium,
                fontSize = (12 * scale).sp,
                lineHeight = (16 * scale).sp,
            ),
        )
    }

    private fun familyOf(token: String): FontFamily =
        when (token.lowercase()) {
            "serif" -> FontFamily.Serif
            "rounded", "cursive" -> FontFamily.Cursive
            "mono" -> FontFamily.Monospace
            else -> FontFamily.SansSerif
        }
}
