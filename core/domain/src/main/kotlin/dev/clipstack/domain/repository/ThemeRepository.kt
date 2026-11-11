package dev.clipstack.domain.repository

import dev.clipstack.domain.model.ThemeConfig
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    /** Emits current resolved theme (local first, then remote when available). */
    val theme: Flow<ThemeConfig>

    /** Force a remote refresh; keeps local on failure. */
    suspend fun refreshFromRemote()
}
