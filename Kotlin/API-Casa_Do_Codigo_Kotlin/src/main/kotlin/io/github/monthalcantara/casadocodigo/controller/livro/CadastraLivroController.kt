package io.github.monthalcantara.casadocodigo.controller.livro

import io.github.monthalcantara.casadocodigo.dto.request.livro.NovoLivroRequest
import io.github.monthalcantara.casadocodigo.dto.response.livro.LivroResponse
import io.github.monthalcantara.casadocodigo.service.CadastraLivroService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/livros")
class CadastraLivroController(private val cadastraLivroService: CadastraLivroService) {

    val log = LoggerFactory.getLogger(CadastraLivroController::class.java)

    @PostMapping
    fun cadastra(@Valid @RequestBody livroRequest: NovoLivroRequest): ResponseEntity<LivroResponse> {

        log.info("Recebida solicitação para cadastro de novo Livro")

        val livro = cadastraLivroService.executa(livroRequest)

        log.info("Preparação objeto de response")

        val response = LivroResponse(livro)

        return ResponseEntity(response, HttpStatus.CREATED)
    }

}