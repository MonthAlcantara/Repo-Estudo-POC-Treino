package io.github.monthalcantara.casadocodigo.service

import io.github.monthalcantara.casadocodigo.model.Autor
import io.github.monthalcantara.casadocodigo.repository.AutorRepository
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.ExampleMatcher.matching
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ConsultaAutorService(val autorRepository: AutorRepository) {

    fun executa(pageable: Pageable): Page<Autor> {
          return autorRepository.findAll(pageable)
    }
}

