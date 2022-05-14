package io.github.monthalcantara.casadocodigo.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.github.monthalcantara.casadocodigo.controller.handler.Erro
import java.time.LocalDateTime

data class ErrorResponse(
    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "dd-MM-yyyy HH:mm"
    )
    var timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val messages: List<Erro>
)
