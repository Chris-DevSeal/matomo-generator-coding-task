package eu.devseal.glasskubematomogenerator.endpoints

import com.fasterxml.jackson.databind.ObjectMapper
import eu.devseal.glasskubematomogenerator.data.MatomoRequest
import eu.devseal.glasskubematomogenerator.logic.MatomoService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(MatomoEndpoint::class)
class MatomoEndpointTest {
    @MockBean
    private lateinit var matomoService: MatomoService

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val url = "/api/v1/matomo-generator"


    @Test
    fun jsonResource() {
        val request = MatomoRequest("TestName", "TestNameSpace", "TestHost")
        val objectMapper = ObjectMapper()
        val json = objectMapper.writeValueAsString(request)
        mockMvc.perform(
            post(url)
                .characterEncoding("UTF-8")
                .contentType(APPLICATION_JSON)
                .content(json)
        )
            .andExpect(
                status()
                    .isOk()
            )
        verify(matomoService).getMatomoJsonFromRequest(request)
    }

    @Test
    fun yamlResource() {
        val request = MatomoRequest("TestName1", "TestNameSpace1", "TestHost1")
        val objectMapper = ObjectMapper()
        val json = objectMapper.writeValueAsString(request)
        mockMvc.perform(
            post("${url}/yaml")
                .characterEncoding("UTF-8")
                .contentType(APPLICATION_JSON)
                .content(json)
        )
            .andExpect(
                status()
                    .isOk()
            )
        verify(matomoService).getMatomoYamlFromRequest(request)
    }

    @Test
    fun getAllResources() {
        mockMvc.perform(
            get("${url}/all")
                .contentType(APPLICATION_JSON)
        ).andExpect(
            status()
                .isOk()
        )
        verify(matomoService).getAllResources()
    }
}