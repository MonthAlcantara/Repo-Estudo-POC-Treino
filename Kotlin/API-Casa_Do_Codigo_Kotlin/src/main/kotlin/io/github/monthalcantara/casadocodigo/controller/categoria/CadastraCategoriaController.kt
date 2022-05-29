package io.github.monthalcantara.casadocodigo.controller.categoria

import io.github.monthalcantara.casadocodigo.dto.NovaCategoriaRequest
import io.github.monthalcantara.casadocodigo.dto.response.CategoriaResponse
import io.github.monthalcantara.casadocodigo.exception.CampoDuplicadoException
import io.github.monthalcantara.casadocodigo.geraLinkHateoas
import io.github.monthalcantara.casadocodigo.repository.CategoriaRepository
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

    @PostMapping
    fun cadastra(@Valid @RequestBody categoriaRequest: NovaCategoriaRequest): ResponseEntity<CategoriaResponse> {
        if(categoriaRepository.findByNome(categoriaRequest.nome) != null) throw CampoDuplicadoException(
            mensagem = "O Nome ${categoriaRequest.nome} ja possui registro no banco",
            campo = "Nome"
        )
        val categoria = categoriaRepository.save(categoriaRequest.toModel())
        val link = categoria.id!!.geraLinkHateoas()
        return ResponseEntity(CategoriaResponse(categoria, link), HttpStatus.CREATED)
    }

}