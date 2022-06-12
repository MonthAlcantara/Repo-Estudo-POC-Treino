package io.github.monthalcantara.casadocodigo.controller.autor

import io.github.monthalcantara.casadocodigo.dto.request.autor.NovoAutorRequest
import io.github.monthalcantara.casadocodigo.dto.response.autor.AutorResponse
import io.github.monthalcantara.casadocodigo.geraLinkHateoas
import io.github.monthalcantara.casadocodigo.service.CadastroAutorService
import org.slf4j.LoggerFactory
import org.springframework.hateoas.Link
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
    fun cadastraNovoAutor(@Valid @RequestBody novoAutor: NovoAutorRequest): ResponseEntity<AutorResponse> {

        log.info("Recebida solicitação para cadastro de novo autor")

        val autorEntity = cadastraAutorService.executa(novoAutor.toEntity())

        log.info("Realizado cadastro de novo autor")

        val link: Link = autorEntity.id!!.geraLinkHateoas()

        log.info("Link Hateoas gerado para o id: ${autorEntity.id}")

        return ResponseEntity.ok(AutorResponse(autorEntity, link))
    }


}