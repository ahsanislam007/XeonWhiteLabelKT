package dev.clipstack.feature.catalog.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.clipstack.domain.model.ThemeSource
import dev.clipstack.feature.catalog.mapper.MoneyMapper
import dev.clipstack.feature.catalog.mvi.CatalogIntent
import dev.clipstack.feature.catalog.mvi.CatalogUiEffect
import dev.clipstack.feature.catalog.mvi.CatalogViewModel
import dev.clipstack.ui.components.CartBar
import dev.clipstack.ui.components.ServiceCard
import dev.clipstack.ui.components.ThemeSourceChip
import dev.clipstack.ui.theme.ClipStackThemeAccessors
import kotlinx.coroutines.flow.collectLatest

/**
 * Shared catalog screen. Brands supply title + ClipStackTheme; this screen stays brand-agnostic.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel,
    title: String,
    themeSource: ThemeSource,
    onRefreshTheme: () -> Unit,
    addLabel: String = "Add",
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbar = remember { SnackbarHostState() }
    val spacing = ClipStackThemeAccessors.spacing

    LaunchedEffect(Unit) {
        viewModel.effects.collectLatest { effect ->
            when (effect) {
                is CatalogUiEffect.ShowMessage -> snackbar.showSnackbar(effect.message)
                CatalogUiEffect.NavigateCheckout ->
                    snackbar.showSnackbar("Checkout stub — wire your booking flow here")
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(title, style = MaterialTheme.typography.titleLarge)
                        Text(
                            state.sourceLabel,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                },
                actions = {
                    ThemeSourceChip(source = themeSource, onRefresh = onRefreshTheme)
                },
            )
        },
        bottomBar = {
            AnimatedVisibility(visible = !state.cart.isEmpty) {
                CartBar(
                    totalLabel = MoneyMapper.formatCents(state.cart.totalCents),
                    itemCount = state.cart.lines.sumOf { it.quantity },
                    onCheckout = { viewModel.onIntent(CatalogIntent.Checkout) },
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbar) },
        containerColor = MaterialTheme.colorScheme.background,
    ) { padding ->
        when {
            state.isLoading && state.services.isEmpty() -> {
                Box(
                    Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center,
                ) { CircularProgressIndicator() }
            }
            state.errorMessage != null && state.services.isEmpty() -> {
                Box(
                    Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center,
                ) { Text(state.errorMessage ?: "") }
            }
            else -> {
                LazyColumn(
                    Modifier.fillMaxSize().padding(padding),
                    contentPadding = PaddingValues(spacing.md),
                    verticalArrangement = Arrangement.spacedBy(spacing.sm),
                ) {
                    items(state.services, key = { it.id }) { item ->
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn() + slideInVertically { it / 4 },
                        ) {
                            ServiceCard(
                                title = item.name,
                                subtitle = item.description,
                                priceLabel = MoneyMapper.formatCents(item.priceCents),
                                metaLabel = "${item.durationMinutes} min · ${item.category}",
                                addLabel = addLabel,
                                onAdd = { viewModel.onIntent(CatalogIntent.AddService(item.id)) },
                            )
                        }
                    }
                }
            }
        }
    }
}
