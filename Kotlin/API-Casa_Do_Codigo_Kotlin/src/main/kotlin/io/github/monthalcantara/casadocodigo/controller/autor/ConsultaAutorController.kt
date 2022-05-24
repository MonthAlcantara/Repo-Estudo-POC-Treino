package io.github.monthalcantara.casadocodigo.controller.autor

import io.github.monthalcantara.casadocodigo.dto.response.NovoAutorResponse
import io.github.monthalcantara.casadocodigo.service.ConsultaAutorService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/autores")
class ConsultaAutorController(val consultaAutorService: ConsultaAutorService) {

    val log = LoggerFactory.getLogger(ConsultaAutorController::class.java)

    @GetMapping
    fun consulta( @PageableDefault(size = 10,sort = ["nome"]) pageable: Pageable): ResponseEntity<Page<NovoAutorResponse>> {

        log.info("Recebida solicitação para consulta de autor")

        val autorEntity = consultaAutorService.executa(pageable)
            .map {NovoAutorResponse(it,  geraLinkHateoas(it.id!!))}

        log.info("Realizado consulta  de autor")

        return ResponseEntity.ok(autorEntity)
    }

    private fun geraLinkHateoas(id: Long): Link {
        log.info("Gerando link para o id: $id")
        return WebMvcLinkBuilder.linkTo(ConsultaAutorController::class.java).slash(id).withSelfRel()
    }

}