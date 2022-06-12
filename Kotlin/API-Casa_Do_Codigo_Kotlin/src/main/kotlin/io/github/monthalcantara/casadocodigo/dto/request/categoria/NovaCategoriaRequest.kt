package io.github.monthalcantara.casadocodigo.dto.request.categoria

import io.github.monthalcantara.casadocodigo.model.Categoria
import javax.validation.constraints.NotBlank

data class NovaCategoriaRequest(
    @field:NotBlank val nome: String
){
    fun toModel() = Categoria(nome = this.nome)
}