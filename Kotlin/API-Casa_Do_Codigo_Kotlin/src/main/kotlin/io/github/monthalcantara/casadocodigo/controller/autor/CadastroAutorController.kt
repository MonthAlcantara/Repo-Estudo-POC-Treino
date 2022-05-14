package io.github.monthalcantara.casadocodigo.controller.autor

import io.github.monthalcantara.casadocodigo.dto.request.NovoAutorRequest
import io.github.monthalcantara.casadocodigo.dto.response.NovoAutorResponse
import io.github.monthalcantara.casadocodigo.repository.AutorRepository
import io.github.monthalcantara.casadocodigo.service.CadastroAutorService
import org.slf4j.LoggerFactory
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/autores")
class CadastroAutorController(val cadastraAutorService: CadastroAutorService) {

    val log = LoggerFactory.getLogger(CadastroAutorController::class.java)

    @PostMapping
    @Transactional
    fun cadastraNovoAutor(@Valid @RequestBody novoAutor: NovoAutorRequest): ResponseEntity<NovoAutorResponse> {

        log.info("Recebida solicitação para cadastro de novo autor")

        val autorEntity = cadastraAutorService.executa(novoAutor.toEntity())

        log.info("Realizado cadastro de novo autor")

        val link = geraLinkHateoas(autorEntity.id)

        log.info("Link Hateoas gerado para o id: ${autorEntity.id}")

        return ResponseEntity.ok(NovoAutorResponse(autorEntity, link))
    }

    private fun geraLinkHateoas(id: Long): Link {
        log.info("Gerando link para o id: $id")
        return WebMvcLinkBuilder.linkTo(CadastroAutorController::class.java).slash(id).withSelfRel()
    }

}