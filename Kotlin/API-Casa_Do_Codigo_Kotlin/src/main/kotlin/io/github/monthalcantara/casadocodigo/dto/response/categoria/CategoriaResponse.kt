package io.github.monthalcantara.casadocodigo.dto.response.categoria

import io.github.monthalcantara.casadocodigo.dto.response.autor.AutorResponse
import io.github.monthalcantara.casadocodigo.geraLinkHateoas
import io.github.monthalcantara.casadocodigo.model.Categoria
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel

data class CategoriaResponse(
    val id: Long?,
    val nome: String,
    val initialLink: Link
) : RepresentationModel<AutorResponse>() {

    constructor(categoria: Categoria, link: Link) : this(id = categoria.id, nome = categoria.nome, initialLink = link)

    companion object {
        fun toResponse(categoria: Categoria): CategoriaResponse {
            val link = categoria.id?.geraLinkHateoas()
            return CategoriaResponse(id = categoria.id, nome = categoria.nome, initialLink = link!!)
        }
    }
}
