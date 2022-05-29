package io.github.monthalcantara.casadocodigo

import io.github.monthalcantara.casadocodigo.controller.autor.CadastroAutorController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder

@SpringBootApplication
class CasadocodigoApplication

fun Long.geraLinkHateoas(): Link {
	return WebMvcLinkBuilder.linkTo(CadastroAutorController::class.java).slash(this).withSelfRel()
}
fun main(args: Array<String>) {
	runApplication<CasadocodigoApplication>(*args)
}
