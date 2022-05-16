package io.github.monthalcantara.forum.controller

import io.github.monthalcantara.forum.dto.request.NovoUsuarioRequest
import io.github.monthalcantara.forum.dto.response.UsuarioResponse
import io.github.monthalcantara.forum.repository.UsuarioRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/usuarios")
class CadastraUsuarioController(val cadastraUsuarioRepository: UsuarioRepository) {

    @PostMapping
    @Transactional
    fun cadastrar(@RequestBody @Valid request: NovoUsuarioRequest): ResponseEntity<UsuarioResponse> {
        val (id, nome, email) = cadastraUsuarioRepository.save(request.toModel())
        val usuarioResponse = UsuarioResponse(id, nome, email)
        return ResponseEntity(usuarioResponse, HttpStatus.CREATED)
    }
}