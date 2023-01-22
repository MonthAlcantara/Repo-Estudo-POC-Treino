package io.github.monthalcantara.estudoaws.controller

import io.github.monthalcantara.estudoaws.dto.PessoaRequest
import io.github.monthalcantara.estudoaws.service.NotificaNovoCadastroService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class CadastraPessoaController(val notificaNovoCadastroService: NotificaNovoCadastroService) {

    @PostMapping
    fun cadastra(@Valid @RequestBody request: PessoaRequest) = notificaNovoCadastroService.notifica(request)
}