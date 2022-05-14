package io.github.monthalcantara.casadocodigo.repository

import io.github.monthalcantara.casadocodigo.model.AutorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.Repository


interface AutorRepository : JpaRepository<AutorEntity, Long> {
    fun findByEmail(email: String): AutorEntity?
    fun save(entity: AutorEntity): AutorEntity
    fun existsByEmail(email: String): Boolean
}