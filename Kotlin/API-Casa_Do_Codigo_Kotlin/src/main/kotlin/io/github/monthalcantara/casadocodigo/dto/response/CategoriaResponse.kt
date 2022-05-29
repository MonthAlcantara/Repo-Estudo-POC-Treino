package io.github.monthalcantara.casadocodigo.dto.response

import io.github.monthalcantara.casadocodigo.model.Categoria
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel

data class CategoriaResponse(
    val id: Long?,
    val nome: String,
    val initialLink: Link
) : RepresentationModel<NovoAutorResponse>() {

constructor(categoria: Categoria, link: Link):this(id = categoria.id, nome = categoria.nome, initialLink = link )
}
