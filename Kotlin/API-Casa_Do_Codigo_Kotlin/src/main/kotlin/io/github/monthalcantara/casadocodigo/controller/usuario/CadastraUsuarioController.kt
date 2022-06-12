package io.github.monthalcantara.casadocodigo.controller.usuario

import io.github.monthalcantara.casadocodigo.dto.request.usuario.NovoUsuarioRequest
import io.github.monthalcantara.casadocodigo.dto.response.usuario.UsuarioResponse
import io.github.monthalcantara.casadocodigo.service.UsuarioService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/usuarios")
class CadastraUsuarioController(val usuarioService: UsuarioService) {

    val log = LoggerFactory.getLogger(CadastraUsuarioController::class.java)

    @PostMapping
    fun cadastra(@Valid @RequestBody usuariorequest: NovoUsuarioRequest): ResponseEntity<UsuarioResponse> {

        log.info("Recebida solicitação para cadastro de novo Usuario")

        val usuarioEntity = usuariorequest.toModel()

        log.info("Realizada conversão de Usuario para objeto de dominio")
        /*
        Conceito chamado Destructuring declarations.
        https://www.linkedin.com/posts/montivaljunior_muitas-vezes-precisamos-recuperar-os-campos-activity-6929840583247044608-Klj7?utm_source=linkedin_share&utm_medium=member_desktop_web
        */
        val (id, username, _, _) = usuarioService.salva(usuarioEntity)

        log.info("Preparação objeto de response")

        return ResponseEntity(UsuarioResponse(id, username), HttpStatus.CREATED)
    }
}