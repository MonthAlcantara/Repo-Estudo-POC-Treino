package io.github.monthalcantara.casadocodigo.controller.handler

import io.github.monthalcantara.casadocodigo.dto.response.ErrorResponse
import io.github.monthalcantara.casadocodigo.exception.CampoDuplicadoException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {

//      Uma outra possibilidade, só que usando um map
//        val map = ex.bindingResult.fieldErrors.map { Pair(it.field, it.defaultMessage) }.toMap()
        val erros = ex.bindingResult.fieldErrors.map { Erro(it.field, it.defaultMessage) }
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            messages = erros,
            error = HttpStatus.BAD_REQUEST.name
        )
        return ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(CampoDuplicadoException::class)
    fun handlerCampoDuplicadoException(ex: CampoDuplicadoException): ErrorResponse {
        return  ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            messages = listOf(Erro(ex.campo, ex.mensagem)),
            error = HttpStatus.BAD_REQUEST.name
        )
    }
}

