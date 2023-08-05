package eu.devseal.glasskubematomogenerator.data

import jakarta.persistence.Embedded

data class MatomoResponse(
    val apiVersion: String = "glasskube.eu/v1alpha1",
    val kind: String = "Matomo",
    @Embedded
    val instanceDetails: MatomoResourceEntity
)