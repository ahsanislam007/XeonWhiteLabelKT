package dev.clipstack.apex.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.clipstack.feature.catalog.mvi.CatalogViewModel
import dev.clipstack.feature.catalog.mvi.ThemeViewModel
import dev.clipstack.feature.catalog.ui.CatalogScreen
import dev.clipstack.ui.theme.WhiteLabelTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class ApexActivity : ComponentActivity() {
    private val catalogViewModel: CatalogViewModel by viewModel()
    private val themeViewModel: ThemeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val theme by themeViewModel.theme.collectAsStateWithLifecycle()
            WhiteLabelTheme(config = theme) {
                CatalogScreen(
                    viewModel = catalogViewModel,
                    title = theme.displayName,
                    themeSource = theme.source,
                    onRefreshTheme = themeViewModel::refresh,
                    addLabel = "Book chair",
                )
            }
        }
    }
}
