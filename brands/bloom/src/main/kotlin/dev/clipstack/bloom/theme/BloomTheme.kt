package dev.clipstack.bloom.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val BloomColors = lightColorScheme(
    primary = Color(0xFF7A5CFF),
    onPrimary = Color.White,
    secondary = Color(0xFFFF8FB8),
    background = Color(0xFFFFF7FB),
    surface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFFF3ECFF),
    onBackground = Color(0xFF2A2140),
    onSurface = Color(0xFF2A2140),
    onSurfaceVariant = Color(0xFF6B6280),
)

private val BloomTypography = Typography(
    titleMedium = TextStyle(
        fontFamily = FontFamily.Cursive,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
)

@Composable
fun BloomTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = BloomColors,
        typography = BloomTypography,
        content = content,
    )
}
