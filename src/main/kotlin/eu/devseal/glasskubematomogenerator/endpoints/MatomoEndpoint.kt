package eu.devseal.glasskubematomogenerator.endpoints

import eu.devseal.glasskubematomogenerator.data.MatomoRequest
import eu.devseal.glasskubematomogenerator.logic.MatomoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/matomo-generator")
class MatomoEndpoint(
    private val matomoService: MatomoService
) {

    @PostMapping
    fun jsonResource(@RequestBody matomoRequest: MatomoRequest) =
        matomoService.getMatomoJsonFromRequest(matomoRequest)

    @PostMapping("yaml")
    fun yamlResource(@RequestBody matomoRequest: MatomoRequest) =
        matomoService.getMatomoYamlFromRequest(matomoRequest)

    @GetMapping("all")
    fun getAllResources() = matomoService.getAllResources()
}