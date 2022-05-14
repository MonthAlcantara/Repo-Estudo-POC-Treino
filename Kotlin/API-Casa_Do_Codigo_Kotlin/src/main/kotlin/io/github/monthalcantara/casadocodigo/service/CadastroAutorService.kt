package io.github.monthalcantara.casadocodigo.service

import io.github.monthalcantara.casadocodigo.exception.CampoDuplicadoException
import io.github.monthalcantara.casadocodigo.model.AutorEntity
import io.github.monthalcantara.casadocodigo.repository.AutorRepository
import org.springframework.stereotype.Service

@Service
class CadastroAutorService(val autorRepository: AutorRepository) {

    fun executa(entity: AutorEntity): AutorEntity {
        if (autorRepository.existsByEmail(entity.email))
            throw CampoDuplicadoException(
                mensagem = "O Email ${entity.email} ja possui registro no banco",
                campo = "email"
            )

        return autorRepository.save(entity)
    }
}

