package io.github.monthalcantara.casadocodigo.dto.request.usuario

import io.github.monthalcantara.casadocodigo.model.Usuario
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class NovoUsuarioRequest(
    @field:NotBlank @field:Email val username: String,
    @field:NotBlank val password: String
) {
    fun toModel(): Usuario {
        val bcript = BCryptPasswordEncoder().encode(this.password)
        return Usuario(username = this.username, password = bcript)
    }
}
