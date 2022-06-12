package io.github.monthalcantara.casadocodigo.repository

import io.github.monthalcantara.casadocodigo.model.Autor
import org.springframework.data.jpa.repository.JpaRepository

interface AutorRepository : JpaRepository<Autor, Long> {
    fun save(entity: Autor): Autor
    fun existsByEmail(email: String): Boolean
}