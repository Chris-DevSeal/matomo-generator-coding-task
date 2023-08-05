package eu.devseal.glasskubematomogenerator.data

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
@Embeddable
data class MatomoResourceEntity(
    @Id
    @JsonIgnore // Don't need it
    val id: UUID = UUID.randomUUID(),
    @Embedded
    val metaData: MatomoMetaData,
    @Embedded
    val spec: MatomoSpec
)
