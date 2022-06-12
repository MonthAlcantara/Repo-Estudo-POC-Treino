package io.github.monthalcantara.casadocodigo.repository

import io.github.monthalcantara.casadocodigo.model.Categoria
import org.springframework.data.jpa.repository.JpaRepository

interface CategoriaRepository: JpaRepository<Categoria, Long> {
    fun existsByNome(nome: String): Boolean

}
