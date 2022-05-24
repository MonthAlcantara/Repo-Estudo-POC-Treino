package io.github.monthalcantara.casadocodigo.repository

import io.github.monthalcantara.casadocodigo.model.UsuarioEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<UsuarioEntity, Long> {
    fun findByUsername(username: String): UsuarioEntity?
}