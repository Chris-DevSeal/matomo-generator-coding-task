package eu.devseal.glasskubematomogenerator.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MatomoRepository : JpaRepository<MatomoResourceEntity, UUID> {
    fun findByMetaData_NamespaceAndMetaData_Name(namespace: String, name: String): MatomoResourceEntity?
}
