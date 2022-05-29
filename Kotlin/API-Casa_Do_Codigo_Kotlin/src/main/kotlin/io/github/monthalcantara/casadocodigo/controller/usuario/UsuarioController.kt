package io.github.monthalcantara.casadocodigo.controller.usuario

import io.github.monthalcantara.casadocodigo.dto.request.usuario.NovoUsuarioRequest
import io.github.monthalcantara.casadocodigo.dto.response.UsuarioResponse
import io.github.monthalcantara.casadocodigo.model.Usuario
import io.github.monthalcantara.casadocodigo.service.UsuarioService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/usuarios")
class UsuarioController(val usuarioService: UsuarioService) {

    @PostMapping
    fun cadastra(@Valid @RequestBody usuariorequest: NovoUsuarioRequest): ResponseEntity<UsuarioResponse> {
        val usuarioEntity = usuariorequest.toModel()

        val (id, username,_,_) = usuarioService.salva(usuarioEntity)
        return ResponseEntity(UsuarioResponse(id, username), HttpStatus.CREATED)
    }
}