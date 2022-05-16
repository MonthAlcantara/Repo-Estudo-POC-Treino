package io.github.monthalcantara.forum.controller

import io.github.monthalcantara.forum.dto.request.NovoTopicoRequest
import io.github.monthalcantara.forum.dto.response.TopicoResponse
import io.github.monthalcantara.forum.service.CadastraTopicoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class CadastraTopicoController(val cadastraTopicoService: CadastraTopicoService) {

    @PostMapping
    fun cadastrar(@RequestBody @Valid request: NovoTopicoRequest): ResponseEntity<TopicoResponse> {
        val topicoView = cadastraTopicoService.executa(request)
        return ResponseEntity(topicoView, HttpStatus.CREATED)
    }
}