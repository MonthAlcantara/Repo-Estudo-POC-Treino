package io.github.monthalcantara.forum.repository

import io.github.monthalcantara.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long>