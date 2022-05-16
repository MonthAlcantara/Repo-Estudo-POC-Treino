package io.github.monthalcantara.forum.dto.request

import io.github.monthalcantara.forum.model.Usuario
import javax.validation.constraints.NotBlank

data class NovoUsuarioRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank val email: String
) {
    fun toModel(): Usuario {
        return Usuario(nome = this.nome, email = this.email)
    }
}
