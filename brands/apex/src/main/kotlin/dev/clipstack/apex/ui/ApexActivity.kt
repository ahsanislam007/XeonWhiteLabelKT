package dev.clipstack.apex.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.clipstack.apex.theme.ApexTheme
import dev.clipstack.feature.catalog.mvi.CatalogViewModel
import dev.clipstack.feature.catalog.ui.CatalogScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class ApexActivity : ComponentActivity() {
    private val catalogViewModel: CatalogViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApexTheme {
                CatalogScreen(
                    viewModel = catalogViewModel,
                    title = "Apex Cuts",
                    addLabel = "Book chair",
                )
            }
        }
    }
}
