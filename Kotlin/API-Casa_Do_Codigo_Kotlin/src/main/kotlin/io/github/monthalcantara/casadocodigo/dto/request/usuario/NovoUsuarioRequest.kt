package io.github.monthalcantara.casadocodigo.dto.request.usuario

import io.github.monthalcantara.casadocodigo.model.UsuarioEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class NovoUsuarioRequest(
    @field:NotBlank @field:Email val username: String,
    @field:NotBlank val password: String
) {
    fun toModel(): UsuarioEntity {
        val bcript = BCryptPasswordEncoder().encode(this.password)
        return UsuarioEntity(username = this.username, password = bcript)
    }
}
