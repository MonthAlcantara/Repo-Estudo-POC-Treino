package io.github.monthalcantara.casadocodigo.dto.request.livro

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.monthalcantara.casadocodigo.model.Autor
import io.github.monthalcantara.casadocodigo.model.Categoria
import io.github.monthalcantara.casadocodigo.model.Livro
import org.hibernate.validator.constraints.ISBN
import java.math.BigDecimal
import javax.validation.constraints.*

data class NovoLivroRequest(
    @field:NotBlank val titulo: String,
    @field:NotBlank @field:Size( max = 500)val resumo: String,
    @field:NotBlank val sumario: String,
    @field:NotNull @field:Min(value = 20) val preco: BigDecimal,
    @field:NotNull @field:Min(value = 100) val numeroPaginas: Int,
    @field:NotBlank @field:ISBN val isbn: String,
    @field:NotNull @JsonProperty("categoria") val idCategoria: Long,
    @field:NotNull @JsonProperty("autor") val idAutor: Long
) {
    fun toModel(autor: Autor, categoria: Categoria) = Livro(
        titulo = this.titulo,
        resumo = this.resumo,
        sumario = this.sumario,
        preco = this.preco,
        numeroPaginas = this.numeroPaginas,
        isbn = this.isbn,
        categoria = categoria,
        autor = autor
    )
}

