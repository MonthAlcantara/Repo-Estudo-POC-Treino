package io.github.monthalcantara.casadocodigo.dto.request.autor

import io.github.monthalcantara.casadocodigo.model.AutorEntity
import java.time.LocalDateTime
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class NovoAutorRequest(
    @field:Email @field:NotBlank val email: String,
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String
) {
    val instanteCriacao: LocalDateTime = LocalDateTime.now()

    fun toEntity() = AutorEntity(
        email = this.email,
        nome = this.nome,
        descricao = this.descricao,
        instanteCriacao = this.instanteCriacao
    )
}

