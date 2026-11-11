package dev.clipstack.domain.usecase

import dev.clipstack.domain.model.ServiceItem
import dev.clipstack.domain.repository.CatalogRepository

class GetServicesUseCase(
    private val catalogRepository: CatalogRepository,
) {
    suspend operator fun invoke(forceRefresh: Boolean = false): Result<List<ServiceItem>> =
        runCatching { catalogRepository.getServices(forceRefresh) }
}
