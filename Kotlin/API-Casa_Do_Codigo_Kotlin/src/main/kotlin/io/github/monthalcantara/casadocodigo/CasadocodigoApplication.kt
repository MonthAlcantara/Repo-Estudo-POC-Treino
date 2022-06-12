package io.github.monthalcantara.casadocodigo

import io.github.monthalcantara.casadocodigo.controller.autor.CadastroAutorController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder

@SpringBootApplication
class CasadocodigoApplication
/*
 Extension functions nos dá a possibilidade de "adicionar" novas funcionalidades
 a uma classe existente, sem ter que trabalhar com heranças.
https://www.linkedin.com/posts/montivaljunior_em-java-quando-temos-blocos-de-c%C3%B3digo-que-activity-6932797006515949568-1O28/?utm_source=linkedin_share&utm_medium=member_desktop_web
 */
fun Long.geraLinkHateoas(): Link {
    return WebMvcLinkBuilder.linkTo(CadastroAutorController::class.java).slash(this).withSelfRel()
}

fun main(args: Array<String>) {
    runApplication<CasadocodigoApplication>(*args)
}
