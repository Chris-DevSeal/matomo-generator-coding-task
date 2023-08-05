package eu.devseal.glasskubematomogenerator.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class JacksonConfiguration {
    @Bean
    fun objectMapper() = ObjectMapper()

    @Bean
    fun yamlObjectMapper(yamlFactory: YAMLFactory) = ObjectMapper(yamlFactory)

    @Bean
    fun yamlFactory() = YAMLFactory()
}