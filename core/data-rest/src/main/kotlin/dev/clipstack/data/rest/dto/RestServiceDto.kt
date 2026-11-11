package dev.clipstack.data.rest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestServiceDto(
    val id: String,
    val title: String,
    val blurb: String,
    @SerialName("price_cents") val priceCents: Int,
    val mins: Int,
    val type: String,
)
