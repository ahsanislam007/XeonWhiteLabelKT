package dev.clipstack.data.common.theme

import dev.clipstack.domain.model.ThemeConfig
import dev.clipstack.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Local-first theme with optional remote override.
 * - Cold start → [localDefault] (instant, offline)
 * - [refreshFromRemote] → paint server/CMS tokens when reachable
 */
class HybridThemeRepository(
    private val localDefault: ThemeConfig,
    private val remoteFetch: suspend () -> RemoteThemePayload?,
) : ThemeRepository {

    private val _theme = MutableStateFlow(localDefault)
    override val theme: Flow<ThemeConfig> = _theme.asStateFlow()

    override suspend fun refreshFromRemote() {
        val payload = runCatching { remoteFetch() }.getOrNull() ?: return
        _theme.value = RemoteThemePayloadMapper.toDomain(payload, localDefault)
    }
}
