package dev.clipstack.bloom.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.clipstack.feature.catalog.mvi.CatalogViewModel
import dev.clipstack.feature.catalog.mvi.ThemeViewModel
import dev.clipstack.feature.catalog.ui.CatalogScreen
import dev.clipstack.ui.theme.ClipStackTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class BloomActivity : ComponentActivity() {
    private val catalogViewModel: CatalogViewModel by viewModel()
    private val themeViewModel: ThemeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val theme by themeViewModel.theme.collectAsStateWithLifecycle()
            ClipStackTheme(config = theme) {
                CatalogScreen(
                    viewModel = catalogViewModel,
                    title = theme.displayName,
                    themeSource = theme.source,
                    onRefreshTheme = themeViewModel::refresh,
                    addLabel = "Reserve",
                )
            }
        }
    }
}
