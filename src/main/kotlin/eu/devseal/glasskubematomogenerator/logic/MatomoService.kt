package eu.devseal.glasskubematomogenerator.logic

import eu.devseal.glasskubematomogenerator.data.*
import eu.devseal.glasskubematomogenerator.exceptions.ResourceAlreadyExistsException
import org.springframework.stereotype.Service

@Service
class MatomoService(
    private val matomoRepository: MatomoRepository,
    private val yamlConverter: YamlConverter
) {
    fun getMatomoJsonFromRequest(matomoRequest: MatomoRequest) = getResourceFromRequest(matomoRequest)

    fun getMatomoYamlFromRequest(matomoRequest: MatomoRequest): String {
        val responseObject = getResourceFromRequest(matomoRequest)
        return yamlConverter.convert(responseObject)
    }

    fun getAllResources(): List<MatomoResponse> =
        matomoRepository.findAll().map { MatomoResponse(instanceDetails = it) }

    private fun getResourceFromRequest(matomoRequest: MatomoRequest): MatomoResponse {
        val possibleResource = matomoRepository.findByMetaData_NamespaceAndMetaData_Name(
            matomoRequest.namespace,
            matomoRequest.name
        )
        possibleResource?.let { throw ResourceAlreadyExistsException("Resource with the namespace '${matomoRequest.namespace}' & name '${matomoRequest.name}' already exists") }
        val resource = createNewMatomoResource(matomoRequest)
        return MatomoResponse(instanceDetails = resource)
    }

    private fun createNewMatomoResource(matomoRequest: MatomoRequest): MatomoResourceEntity {
        val metaData = MatomoMetaData(matomoRequest.name, matomoRequest.namespace)
        val spec = MatomoSpec(matomoRequest.host)
        val responseToBeSaved = MatomoResourceEntity(metaData = metaData, spec = spec)
        return matomoRepository.save(responseToBeSaved)
    }


}
