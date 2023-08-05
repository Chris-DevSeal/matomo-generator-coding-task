package eu.devseal.glasskubematomogenerator.logic

import com.fasterxml.jackson.databind.ObjectMapper
import eu.devseal.glasskubematomogenerator.exceptions.ProcessingException
import org.springframework.stereotype.Service

@Service
class YamlConverter(private val yamlObjectMapper: ObjectMapper) {
    fun <T> convert(t: T) =
        yamlObjectMapper.writeValueAsString(t) ?: throw ProcessingException("An error occurred")

//    fun <T> convertAll(t: List<T>)
}
