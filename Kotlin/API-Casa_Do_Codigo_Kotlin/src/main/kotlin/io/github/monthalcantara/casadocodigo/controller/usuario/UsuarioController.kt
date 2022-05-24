package io.github.monthalcantara.casadocodigo.controller.usuario

import io.github.monthalcantara.casadocodigo.dto.request.usuario.NovoUsuarioRequest
import io.github.monthalcantara.casadocodigo.model.UsuarioEntity
import io.github.monthalcantara.casadocodigo.service.UsuarioService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/usuarios")
class UsuarioController(val usuarioService: UsuarioService) {

    @PostMapping
    fun cadastra(@Valid @RequestBody usuariorequest: NovoUsuarioRequest): UsuarioEntity {
        val usuarioEntity = usuariorequest.toModel()

       return usuarioService.salva(usuarioEntity)
    }
}