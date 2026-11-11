package dev.clipstack.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import dev.clipstack.domain.model.ThemeConfig
import dev.clipstack.domain.model.ThemeSource

val LocalThemeConfig = staticCompositionLocalOf<ThemeConfig> {
    error("ThemeConfig not provided. Wrap with ClipStackTheme.")
}

object ClipStackThemeAccessors {
    val config: ThemeConfig
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeConfig.current

    val spacing: ClipStackSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalClipStackSpacing.current
}

/**
 * App-wide Material 3 theme driven by a [ThemeConfig]
 * that can come from local bundles or a remote CMS/API.
 */
@Composable
fun ClipStackTheme(
    config: ThemeConfig,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val colorScheme = ThemeConfigToMaterialMapper.colorScheme(config)
    val typography = ThemeConfigToMaterialMapper.typography(config)
    val shapes = ThemeConfigToMaterialMapper.shapes(config)

    CompositionLocalProvider(
        LocalThemeConfig provides config,
        LocalClipStackSpacing provides ClipStackSpacing(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = shapes,
        ) {
            Surface(
                modifier = modifier,
                color = MaterialTheme.colorScheme.background,
                content = content,
            )
        }
    }
}

fun ThemeSource.label(): String =
    when (this) {
        ThemeSource.LOCAL -> "Local theme"
        ThemeSource.REMOTE -> "Server theme"
    }
