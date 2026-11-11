package dev.clipstack.apex.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val ApexColors = darkColorScheme(
    primary = Color(0xFFE8B86D),
    onPrimary = Color(0xFF1A1208),
    secondary = Color(0xFF8D6E63),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    surfaceVariant = Color(0xFF2A2A2A),
    onBackground = Color(0xFFF5F0E8),
    onSurface = Color(0xFFF5F0E8),
    onSurfaceVariant = Color(0xFFBCAAA4),
)

private val ApexTypography = Typography(
    titleMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 14.sp,
    ),
)

@Composable
fun ApexTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ApexColors,
        typography = ApexTypography,
        content = content,
    )
}
