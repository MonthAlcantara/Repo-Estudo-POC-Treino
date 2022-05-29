package io.github.monthalcantara.casadocodigo.repository

import io.github.monthalcantara.casadocodigo.model.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByNome(nome: String): Role?
}