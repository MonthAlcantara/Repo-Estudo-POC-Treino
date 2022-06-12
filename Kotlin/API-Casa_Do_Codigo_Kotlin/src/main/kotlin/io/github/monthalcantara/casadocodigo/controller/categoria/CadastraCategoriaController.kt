package io.github.monthalcantara.casadocodigo.controller.categoria

import io.github.monthalcantara.casadocodigo.dto.request.categoria.NovaCategoriaRequest
import io.github.monthalcantara.casadocodigo.dto.response.categoria.CategoriaResponse
import io.github.monthalcantara.casadocodigo.exception.CampoDuplicadoException
import io.github.monthalcantara.casadocodigo.geraLinkHateoas
import io.github.monthalcantara.casadocodigo.repository.CategoriaRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/categorias")
class CadastraCategoriaController(val categoriaRepository: CategoriaRepository) {

    val log = LoggerFactory.getLogger(CadastraCategoriaController::class.java)

    @PostMapping
    fun cadastra(@Valid @RequestBody categoriaRequest: NovaCategoriaRequest): ResponseEntity<CategoriaResponse> {

        log.info("Recebida solicitação para cadastro de nova Categoria")

        if(categoriaRepository.existsByNome(categoriaRequest.nome)) {
            log.error("Categoria: {} ja possui registro na base", categoriaRequest.nome)
            throw CampoDuplicadoException(
                mensagem = "O Nome ${categoriaRequest.nome} ja possui registro no banco",
                campo = "Nome"
            )
        }

        log.info("Inciando processo de salvamento de nova Categoria")

        val categoria = categoriaRepository.save(categoriaRequest.toModel())

        log.info("Gerando link para o id: ${categoria.id}")

        val link = categoria.id!!.geraLinkHateoas()

        log.info("Preparação objeto de response")

        return ResponseEntity(CategoriaResponse(categoria, link), HttpStatus.CREATED)
    }

}