package io.github.monthalcantara.forum.dto.request

import io.github.monthalcantara.forum.model.Curso
import io.github.monthalcantara.forum.model.Topico
import io.github.monthalcantara.forum.model.Usuario
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NovoTopicoRequest(
    @field:NotBlank(message = "Titulo nao pode ser em branco")
    @field:Size(min = 5, max = 100, message = "Titulo deve ter entre 5 e 100 caracteres")
    val titulo: String,
    @field:NotBlank(message = "Mensagem nao pode ser em branco")
    val mensagem: String,
    @field:NotNull
    val idCurso: Long,
    @field:NotNull
    val idAutor: Long
) {
    fun toEntity(usuario: Usuario, curso: Curso): Topico {
        return Topico(titulo = this.titulo, mensagem = this.mensagem, autor = usuario, curso = curso)
    }
}