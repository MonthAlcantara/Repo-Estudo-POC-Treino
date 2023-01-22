package io.github.monthalcantara.estudoaws.model

import javax.validation.constraints.NotBlank

data class Pessoa(
    @field:NotBlank val nome: String,
    @field:NotBlank val email: String
)
