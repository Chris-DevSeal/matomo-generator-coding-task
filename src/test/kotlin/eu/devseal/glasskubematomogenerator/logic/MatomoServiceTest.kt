package eu.devseal.glasskubematomogenerator.logic

import eu.devseal.glasskubematomogenerator.data.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class MatomoServiceTest {
    private val matomoRepository = mock(MatomoRepository::class.java)
    private val yamlConverter = mock(YamlConverter::class.java)
    private val matomoService = MatomoService(matomoRepository, yamlConverter)

    private val matomoRequest = MatomoRequest("Test", "Test", "Test")
    private val metaData = MatomoMetaData(matomoRequest.name, matomoRequest.namespace)
    private val spec = MatomoSpec(matomoRequest.host)

    @Test
    fun getMatomoJsonFromRequest() {
        `when`(
            matomoRepository.save(any(MatomoResourceEntity::class.java))
        ).thenReturn(
            MatomoResourceEntity(metaData = metaData, spec = spec)
        )
        matomoService.getMatomoJsonFromRequest(matomoRequest)
        verify(matomoRepository)
            .findByMetaData_NamespaceAndMetaData_Name(namespace = matomoRequest.namespace, name = matomoRequest.name)
        verify(matomoRepository)
            .save(any(MatomoResourceEntity::class.java))
    }

    @Test
    fun getMatomoYamlFromRequest() {
        `when`(
            matomoRepository.save(any(MatomoResourceEntity::class.java))
        ).thenReturn(
            MatomoResourceEntity(metaData = metaData, spec = spec)
        )
        matomoService.getMatomoYamlFromRequest(matomoRequest)
        verify(matomoRepository)
            .findByMetaData_NamespaceAndMetaData_Name(namespace = matomoRequest.namespace, name = matomoRequest.name)
        verify(matomoRepository)
            .save(any(MatomoResourceEntity::class.java))
        verify(yamlConverter).convert(any(MatomoResponse::class.java))
    }

    @Test
    fun getAllResources() {
        matomoService.getAllResources()
        verify(matomoRepository).findAll()
    }
}