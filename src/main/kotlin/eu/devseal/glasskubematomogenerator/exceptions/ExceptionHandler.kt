package eu.devseal.glasskubematomogenerator.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<String> {
        val responseBody = "An error occurred: ${e.message}"
        val status = determineHttpStatus(e)
        return ResponseEntity(responseBody, status)
    }

    private fun determineHttpStatus(ex: Exception): HttpStatus {
        return when (ex) {
            is ProcessingException -> HttpStatus.INTERNAL_SERVER_ERROR
            is ResourceAlreadyExistsException -> HttpStatus.BAD_REQUEST
            else -> HttpStatus.I_AM_A_TEAPOT //Sorry for using this status. I just love its vibe (I promise you, that won't happen in production)
        }
    }

}