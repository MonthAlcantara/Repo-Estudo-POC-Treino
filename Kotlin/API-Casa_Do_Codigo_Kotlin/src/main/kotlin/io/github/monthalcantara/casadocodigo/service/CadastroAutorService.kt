package io.github.monthalcantara.casadocodigo.service

import io.github.monthalcantara.casadocodigo.exception.CampoDuplicadoException
import io.github.monthalcantara.casadocodigo.model.Autor
import io.github.monthalcantara.casadocodigo.repository.AutorRepository
import org.springframework.stereotype.Service

@Service
class CadastroAutorService(val autorRepository: AutorRepository) {

    fun executa(autor: Autor): Autor {
        if (autorRepository.existsByEmail(autor.email))
            throw CampoDuplicadoException(
                mensagem = "O Email ${autor.email} ja possui registro no banco",
                campo = "email"
            )

        return autorRepository.save(autor)
    }
}

