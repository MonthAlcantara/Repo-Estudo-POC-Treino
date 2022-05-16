package io.github.monthalcantara.casadocodigo.controller.handler

import io.github.monthalcantara.casadocodigo.dto.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValid(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val validationsErrors = ex.bindingResult.fieldErrors.map { Pair(it.field, it.defaultMessage) }.toMap()
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            validations = validationsErrors
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}
