package io.github.monthalcantara.casadocodigo.dto.response.autor

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.github.monthalcantara.casadocodigo.geraLinkHateoas
import io.github.monthalcantara.casadocodigo.model.Autor
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class AutorResponse(
    var id: Long? = null,
    val email: String,
    val nome: String,
    val descricao: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") val instanteCriacao: LocalDateTime,
    val initialLink: Link
) : RepresentationModel<AutorResponse>() {
    constructor(autor: Autor, initialLink: Link) : this(
        id = autor.id,
        email = autor.email,
        nome = autor.nome,
        descricao = autor.descricao,
        instanteCriacao = autor.instanteCriacao, initialLink = initialLink
    )
    companion object {
        fun toResponse(autor: Autor): AutorResponse {
            val link = autor.id?.geraLinkHateoas()
            return AutorResponse(
                id = autor.id,
                email = autor.email,
                nome = autor.nome,
                descricao = autor.descricao,
                instanteCriacao = autor.instanteCriacao, initialLink = link!!)
        }
    }

}

