package io.github.monthalcantara.casadocodigo.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.github.monthalcantara.casadocodigo.model.AutorEntity
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class NovoAutorResponse(
    var id: Long? = null,
    val email: String,
    val nome: String,
    val descricao: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") val instanteCriacao: LocalDateTime,
    val initialLink: Link
) : RepresentationModel<NovoAutorResponse>() {
    constructor(autorEntity: AutorEntity, initialLink: Link) : this(
        id = autorEntity.id, email = autorEntity.email,
        nome = autorEntity.email,
        descricao = autorEntity.descricao,
        instanteCriacao = autorEntity.instanteCriacao, initialLink = initialLink
    )


}

