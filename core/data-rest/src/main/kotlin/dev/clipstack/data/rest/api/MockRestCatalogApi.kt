package dev.clipstack.data.rest.api

import dev.clipstack.data.rest.dto.RestServiceDto
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

/**
 * Stand-in for a real OkHttp/Retrofit call to "Server A".
 * Swap [fetchServicesJson] with a network call when you have a backend.
 */
class MockRestCatalogApi {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun fetchServices(): List<RestServiceDto> {
        delay(450) // simulate network
        return json.decodeFromString(SAMPLE_JSON)
    }

    companion object {
        // Pretend this came from https://api.server-a.example/v1/services
        private val SAMPLE_JSON = """
            [
              {"id":"apex-fade","title":"Signature Fade","blurb":"Skin fade with hot towel finish","price_cents":3500,"mins":35,"type":"haircut"},
              {"id":"apex-beard","title":"Beard Sculpt","blurb":"Line-up + oil treatment","price_cents":1800,"mins":20,"type":"beard"},
              {"id":"apex-kids","title":"Kids Cut","blurb":"Under 12, patient stylists","price_cents":2200,"mins":25,"type":"haircut"},
              {"id":"apex-color","title":"Grey Blend","blurb":"Natural blend for salt & pepper","price_cents":4500,"mins":45,"type":"color"}
            ]
        """.trimIndent()
    }
}
