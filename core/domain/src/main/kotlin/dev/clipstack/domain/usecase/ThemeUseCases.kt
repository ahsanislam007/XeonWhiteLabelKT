package dev.clipstack.domain.usecase

import dev.clipstack.domain.model.ThemeConfig
import dev.clipstack.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

class ObserveThemeUseCase(
    private val themeRepository: ThemeRepository,
) {
    operator fun invoke(): Flow<ThemeConfig> = themeRepository.theme
}

class RefreshThemeUseCase(
    private val themeRepository: ThemeRepository,
) {
    suspend operator fun invoke() = themeRepository.refreshFromRemote()
}
