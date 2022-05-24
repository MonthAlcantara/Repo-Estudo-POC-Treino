package io.github.monthalcantara.forum.controller

import io.github.monthalcantara.forum.dto.request.NovoTopicoRequest
import io.github.monthalcantara.forum.dto.response.TopicoResponse
import io.github.monthalcantara.forum.model.Curso
import io.github.monthalcantara.forum.model.Topico
import io.github.monthalcantara.forum.model.Usuario
import io.github.monthalcantara.forum.repository.TopicoRepository
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/topicos")
class ConsultaTopicosController(val topicoRepository: TopicoRepository) {


//    @GetMapping
//    fun consulta(@RequestBody topicoRequest: NovoTopicoRequest): List<TopicoResponse> {
//        val matcher = ExampleMatcher
//            .matching()
//            .withIgnoreCase()
//            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
//
//        val example = Example.of(topicoRequest.toEntity(Usuario(nome = "x", email = "y"), Curso(nome="String",
//            categoria= "String")), matcher)
//
//        val findAll: List<Topico> = topicoRepository.findAll(example) as List<Topico>
//        return findAll.map { TopicoResponse.toResponse(it) }..toList()
//    }
}
