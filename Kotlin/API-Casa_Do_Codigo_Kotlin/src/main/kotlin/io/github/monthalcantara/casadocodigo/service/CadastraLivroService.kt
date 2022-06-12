package io.github.monthalcantara.casadocodigo.service

import io.github.monthalcantara.casadocodigo.dto.request.livro.NovoLivroRequest
import io.github.monthalcantara.casadocodigo.model.Livro
import io.github.monthalcantara.casadocodigo.repository.CategoriaRepository
import io.github.monthalcantara.casadocodigo.repository.LivroRepository
import io.github.monthalcantara.casadocodigo.validation.ValidaLivroRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CadastraLivroService(
    private val consultaAutorService: ConsultaAutorService,
    private val categoriaRepository: CategoriaRepository,
    private val livroRepository: LivroRepository,
    private val validaLivroRequest: ValidaLivroRequest

) {
    val log = LoggerFactory.getLogger(CadastraLivroService::class.java)

    @Transactional
    fun executa(livroRequest: NovoLivroRequest): Livro {
        log.info("Iniciando processo de registro de novo livro")

        validaLivroRequest.valida(livroRequest, livroRepository)

        val autor = consultaAutorService.buscaPorId(livroRequest.idAutor)

        log.info("Encontrado autor: {} pelo id da request", autor.nome)

        val categoria = categoriaRepository.findById(livroRequest.idCategoria).orElseThrow { IllegalAccessError("Não existe categoria com o Id informado") }

        log.info("Encontrada categoria: {} pelo id da request", categoria.nome)

        val livro = livroRequest.toModel(autor = autor, categoria = categoria)

        log.info("LivroRequest convertido para objeto de domínio")

        return livroRepository.save(livro)
    }
}

