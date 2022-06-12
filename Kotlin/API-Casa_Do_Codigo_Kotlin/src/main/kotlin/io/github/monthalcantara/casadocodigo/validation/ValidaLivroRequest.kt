package io.github.monthalcantara.casadocodigo.validation

import io.github.monthalcantara.casadocodigo.dto.request.livro.NovoLivroRequest
import io.github.monthalcantara.casadocodigo.exception.CampoDuplicadoException
import io.github.monthalcantara.casadocodigo.repository.LivroRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ValidaLivroRequest {

    val log = LoggerFactory.getLogger(ValidaLivroRequest::class.java)

    fun valida(livroRequest: NovoLivroRequest, repository: LivroRepository) {

        log.info("Iniciando a validação de campos do request de novo Livro")

        when {
            repository.existsByTitulo(livroRequest.titulo) -> throw CampoDuplicadoException(
                mensagem = "Ja existe um livro com esse titulo",
                campo = "Título"
            )
            repository.existsByIsbn(livroRequest.isbn) -> throw CampoDuplicadoException(
                mensagem = "Ja existe um livro com esse isbn",
                campo = "ISBN"
            )
        }
    }
}
