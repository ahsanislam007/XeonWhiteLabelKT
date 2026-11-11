package dev.clipstack.domain.model

/**
 * Brand-agnostic theme contract.
 * Hex colors are `#RRGGBB` or `#AARRGGBB`. UI maps this into Material 3.
 */
data class ThemeConfig(
    val brandId: String,
    val displayName: String,
    val source: ThemeSource,
    val isDark: Boolean,
    val colors: ThemeColorTokens,
    val shapes: ThemeShapeTokens = ThemeShapeTokens(),
    val typography: ThemeTypeTokens = ThemeTypeTokens(),
)

enum class ThemeSource {
    /** Bundled in the APK (offline-safe default). */
    LOCAL,
    /** Fetched from a remote config / CMS / BFF. */
    REMOTE,
}

data class ThemeColorTokens(
    val primary: String,
    val onPrimary: String,
    val primaryContainer: String,
    val onPrimaryContainer: String,
    val secondary: String,
    val onSecondary: String,
    val secondaryContainer: String,
    val onSecondaryContainer: String,
    val tertiary: String,
    val onTertiary: String,
    val background: String,
    val onBackground: String,
    val surface: String,
    val onSurface: String,
    val surfaceVariant: String,
    val onSurfaceVariant: String,
    val outline: String,
    val error: String = "#B3261E",
    val onError: String = "#FFFFFF",
)

data class ThemeShapeTokens(
    /** Corner radius in dp for small components (chips, text fields). */
    val smallDp: Int = 8,
    /** Cards / buttons. */
    val mediumDp: Int = 16,
    /** Sheets / dialogs. */
    val largeDp: Int = 28,
)

data class ThemeTypeTokens(
    /** display / headline / title font role: serif | sans | rounded */
    val displayFamily: String = "sans",
    val bodyFamily: String = "sans",
    val scale: Float = 1.0f,
)
