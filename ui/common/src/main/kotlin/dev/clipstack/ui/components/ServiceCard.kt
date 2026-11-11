package dev.clipstack.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import dev.clipstack.ui.theme.WhiteLabelThemeAccessors

/**
 * Shared M3 card — colors/shapes come from [MaterialTheme] / XeonWhiteLabelKT design tokens.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceCard(
    title: String,
    subtitle: String,
    priceLabel: String,
    metaLabel: String,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier,
    addLabel: String = "Add",
) {
    val spacing = WhiteLabelThemeAccessors.spacing
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.98f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "cardScale",
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        onClick = { pressed = !pressed },
    ) {
        Column(
            Modifier.padding(spacing.md),
            verticalArrangement = Arrangement.spacedBy(spacing.xs),
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(
                subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text(priceLabel, style = MaterialTheme.typography.titleSmall)
                    Text(metaLabel, style = MaterialTheme.typography.labelMedium)
                }
                Button(onClick = onAdd, shape = MaterialTheme.shapes.small) {
                    Text(addLabel)
                }
            }
        }
    }
}

@Composable
fun CartBar(
    totalLabel: String,
    itemCount: Int,
    onCheckout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = WhiteLabelThemeAccessors.spacing
    Row(
        modifier
            .fillMaxWidth()
            .padding(spacing.md),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("$itemCount in cart · $totalLabel", style = MaterialTheme.typography.titleSmall)
        TextButton(onClick = onCheckout) { Text("Book") }
    }
}
