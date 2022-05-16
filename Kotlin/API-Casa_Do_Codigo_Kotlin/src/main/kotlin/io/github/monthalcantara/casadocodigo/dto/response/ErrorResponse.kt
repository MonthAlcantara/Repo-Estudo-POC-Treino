package io.github.monthalcantara.casadocodigo.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ErrorResponse(
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy HH:mm")
    var timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val validations: Map<String, String?>
)
