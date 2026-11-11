package dev.clipstack.ui.components

import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.clipstack.domain.model.ThemeSource
import dev.clipstack.ui.theme.label

@Composable
fun ThemeSourceChip(
    source: ThemeSource,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AssistChip(
        onClick = onRefresh,
        modifier = modifier,
        label = { Text(source.label()) },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            labelColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
    )
}
