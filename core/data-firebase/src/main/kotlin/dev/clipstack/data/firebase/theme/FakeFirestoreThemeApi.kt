package dev.clipstack.data.firebase.theme

import dev.clipstack.data.common.theme.RemoteThemePayload
import kotlinx.coroutines.delay

/**
 * Simulates a Firestore document: brands/bloom/theme
 */
class FakeFirestoreThemeApi {
    suspend fun fetchThemeDoc(): RemoteThemePayload {
        delay(420)
        return RemoteThemePayload(
            brandId = "bloom",
            displayName = "Bloom Studio",
            isDark = false,
            colors = mapOf(
                "primary" to "#6C4DFF",
                "onPrimary" to "#FFFFFF",
                "primaryContainer" to "#EDE7FF",
                "onPrimaryContainer" to "#1F0060",
                "secondary" to "#FF7AAB",
                "onSecondary" to "#3B071F",
                "secondaryContainer" to "#FFE0EC",
                "onSecondaryContainer" to "#3B071F",
                "tertiary" to "#2EC4B6",
                "onTertiary" to "#003733",
                "background" to "#FFF9FC",
                "onBackground" to "#2A2140",
                "surface" to "#FFFFFF",
                "onSurface" to "#2A2140",
                "surfaceVariant" to "#F0E9FF",
                "onSurfaceVariant" to "#5E5678",
                "outline" to "#B8A9F5",
            ),
            shapes = mapOf("smallDp" to 14, "mediumDp" to 22, "largeDp" to 36),
            typography = mapOf("displayFamily" to "rounded", "bodyFamily" to "sans", "scale" to "1.03"),
        )
    }
}
