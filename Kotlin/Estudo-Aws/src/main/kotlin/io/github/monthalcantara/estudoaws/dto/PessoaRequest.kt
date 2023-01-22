package io.github.monthalcantara.estudoaws.dto

import javax.validation.constraints.NotBlank

data class PessoaRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank val email: String
)
