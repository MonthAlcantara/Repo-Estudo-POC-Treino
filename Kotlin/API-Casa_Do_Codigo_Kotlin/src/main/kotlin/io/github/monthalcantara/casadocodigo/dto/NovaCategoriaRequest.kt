package io.github.monthalcantara.casadocodigo.dto

import io.github.monthalcantara.casadocodigo.model.Categoria

data class NovaCategoriaRequest(
    val nome: String
){
    fun toModel() = Categoria(nome = this.nome)
}