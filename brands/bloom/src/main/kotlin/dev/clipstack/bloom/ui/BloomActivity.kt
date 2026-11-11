package dev.clipstack.bloom.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.clipstack.bloom.theme.BloomTheme
import dev.clipstack.feature.catalog.mvi.CatalogViewModel
import dev.clipstack.feature.catalog.ui.CatalogScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class BloomActivity : ComponentActivity() {
    private val catalogViewModel: CatalogViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BloomTheme {
                CatalogScreen(
                    viewModel = catalogViewModel,
                    title = "Bloom Studio",
                    addLabel = "Reserve",
                )
            }
        }
    }
}
