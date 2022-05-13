package io.github.monthalcantara.casadocodigo.repository

import io.github.monthalcantara.casadocodigo.model.AutorEntity
import org.springframework.data.repository.CrudRepository

interface AutorRepository: CrudRepository<AutorEntity, Long> {
}