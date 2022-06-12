package io.github.monthalcantara.casadocodigo.repository

import io.github.monthalcantara.casadocodigo.model.Livro
import org.springframework.data.jpa.repository.JpaRepository

interface LivroRepository: JpaRepository<Livro, Long> {
    fun existsByTitulo(titulo: String): Boolean
    fun existsByIsbn(isbn: String): Boolean
}