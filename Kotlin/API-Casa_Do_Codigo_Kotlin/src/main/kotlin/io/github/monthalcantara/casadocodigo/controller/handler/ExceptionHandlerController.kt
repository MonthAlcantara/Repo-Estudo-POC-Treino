package io.github.monthalcantara.casadocodigo.controller.handler

import io.github.monthalcantara.casadocodigo.dto.response.error.ErrorResponse
import io.github.monthalcantara.casadocodigo.exception.CampoDuplicadoException
import io.github.monthalcantara.casadocodigo.exception.NaoAutorizadoException
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

    @ExceptionHandler(NaoAutorizadoException::class)
    fun handlerNaoAutorizadoException(ex: NaoAutorizadoException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.FORBIDDEN.value(),
            error = HttpStatus.FORBIDDEN.name,
            validations = mapOf(Pair("mensagem:", ex.mensagem))
        )
        return ResponseEntity(errorResponse, HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(CampoDuplicadoException::class)
    fun handlerCampoDuplicadoException(ex: CampoDuplicadoException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            /*Poderia simplificar aqui usando a epression to, indicando se trata de key e value
            validations = mapOf("${ex.mensagem}:" to ex.campo)
            Mas preferi usar o Pair https://www.linkedin.com/posts/montivaljunior_ter-a-possibilidade-de-criar-um-m%C3%A9todo-que-activity-6931663211410665473-G-sp?utm_source=linkedin_share&utm_medium=member_desktop_web
            */
            validations = mapOf(Pair("${ex.mensagem}:", ex.campo))
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handlerIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            validations = mapOf(Pair("mensagem:", ex.message))
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}
