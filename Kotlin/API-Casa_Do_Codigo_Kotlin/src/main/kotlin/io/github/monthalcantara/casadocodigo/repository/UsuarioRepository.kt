package io.github.monthalcantara.casadocodigo.repository

import io.github.monthalcantara.casadocodigo.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
    fun findByUsername(username: String?): Usuario?
}