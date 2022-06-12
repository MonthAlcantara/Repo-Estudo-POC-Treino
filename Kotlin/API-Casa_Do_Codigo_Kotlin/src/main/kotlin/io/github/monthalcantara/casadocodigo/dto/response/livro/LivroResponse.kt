package io.github.monthalcantara.casadocodigo.dto.response.livro

import io.github.monthalcantara.casadocodigo.dto.response.autor.AutorResponse
import io.github.monthalcantara.casadocodigo.dto.response.categoria.CategoriaResponse
import io.github.monthalcantara.casadocodigo.geraLinkHateoas
import io.github.monthalcantara.casadocodigo.model.Livro
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal

data class LivroResponse (
    val titulo: String,
    val resumo: String,
    val sumario: String,
    val preco: BigDecimal,
    val numeroPaginas: Int,
    val isbn: String,
    val categoria: CategoriaResponse,
    val autor: AutorResponse,
    val initialLink: Link
) : RepresentationModel<AutorResponse>(){
    constructor(livro: Livro) : this(
        titulo = livro.titulo,
        resumo = livro.resumo,
        sumario = livro.sumario,
        preco = livro.preco,
        numeroPaginas = livro.numeroPaginas,
        isbn = livro.isbn,
        categoria = CategoriaResponse.toResponse(livro.categoria),
        autor = AutorResponse.toResponse(livro.autor),initialLink = livro.id?.geraLinkHateoas()!!
        )

}
