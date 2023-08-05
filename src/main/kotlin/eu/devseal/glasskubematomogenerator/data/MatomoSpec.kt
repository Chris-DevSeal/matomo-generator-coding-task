package eu.devseal.glasskubematomogenerator.data

import jakarta.persistence.Embeddable

@Embeddable
data class MatomoSpec(
    val host: String
)
