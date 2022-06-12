package io.github.monthalcantara.casadocodigo.service

import io.github.monthalcantara.casadocodigo.model.Autor
import io.github.monthalcantara.casadocodigo.repository.AutorRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ConsultaAutorService(val autorRepository: AutorRepository) {

    @Transactional
    fun executa(pageable: Pageable) = autorRepository.findAll(pageable)

    @Transactional
    fun buscaPorId(id: Long): Autor =
        autorRepository.findById(id).orElseThrow{IllegalArgumentException("Não existe autor com o Id informado")}

}

