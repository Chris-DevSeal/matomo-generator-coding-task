package eu.devseal.glasskubematomogenerator.data

import jakarta.persistence.Embeddable

@Embeddable
data class MatomoMetaData(
    val name: String,
    val namespace: String
)
