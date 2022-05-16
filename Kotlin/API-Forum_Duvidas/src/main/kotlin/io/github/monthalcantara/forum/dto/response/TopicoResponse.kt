package io.github.monthalcantara.forum.dto.response

import io.github.monthalcantara.forum.model.StatusTopico
import io.github.monthalcantara.forum.model.Topico
import java.time.LocalDateTime

class TopicoResponse(
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime
) {
    companion object {
        fun toResponse(topico: Topico): TopicoResponse {
            val (id, titulo, mensagem, dataCriacao, _, _, statusTopico, _) = topico
            return TopicoResponse(id, titulo, mensagem, statusTopico, dataCriacao)
        }
    }
}