package dev.clipstack.data.firebase.mapper

import dev.clipstack.data.firebase.dto.FirestoreServiceDoc
import dev.clipstack.domain.model.ServiceCategory
import dev.clipstack.domain.model.ServiceItem

object FirestoreServiceMapper {
    fun toDomain(doc: FirestoreServiceDoc): ServiceItem? {
        val name = doc.fields["name"] ?: return null
        val priceDollars = doc.fields["price"]?.toDoubleOrNull() ?: return null
        val duration = doc.fields["duration"]?.toIntOrNull() ?: 30
        val tag = doc.fields["tag"].orEmpty()
        return ServiceItem(
            id = doc.docId,
            name = name,
            description = doc.fields["summary"].orEmpty(),
            priceCents = (priceDollars * 100).toInt(),
            durationMinutes = duration,
            category = when (tag.lowercase()) {
                "haircut" -> ServiceCategory.HAIRCUT
                "beard" -> ServiceCategory.BEARD
                "color" -> ServiceCategory.COLOR
                "styling" -> ServiceCategory.STYLING
                else -> ServiceCategory.OTHER
            },
        )
    }
}
