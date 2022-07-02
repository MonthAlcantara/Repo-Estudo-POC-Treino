package io.github.monthalcantara.casadocodigo.service

import io.github.monthalcantara.casadocodigo.dto.request.livro.NovoLivroRequest
import io.github.monthalcantara.casadocodigo.exception.CampoDuplicadoException
import io.github.monthalcantara.casadocodigo.model.Autor
import io.github.monthalcantara.casadocodigo.model.Categoria
import io.github.monthalcantara.casadocodigo.model.Livro
import io.github.monthalcantara.casadocodigo.repository.CategoriaRepository
import io.github.monthalcantara.casadocodigo.repository.LivroRepository
import io.github.monthalcantara.casadocodigo.validation.ValidaLivroRequest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

class CadastraLivroServiceTest {
    private var autor = Autor(1L, "teste@teste.com", "Nome", "descricao", LocalDateTime.now())
    private var categoria = Categoria(1L, "categoria 1")
    private var livro = Livro(
        1L,
        "titulo",
        "resumo",
        "sumario",
        BigDecimal.valueOf(25L),
        40,
        "isbn",
        categoria,
        autor
    )

    private val livroRequest = NovoLivroRequest(
        "titulo",
        "resumo",
        "sumario",
        BigDecimal.valueOf(25L),
        40,
        "isbn",
        1L,
        1L
    )

    //Dentro da declaração do mock eu ja posso dizer o que ele deve fazer
    private val consultaAutorService: ConsultaAutorService = mockk { every { buscaPorId(any()) } returns autor }
    private val categoriaRepository: CategoriaRepository =
        mockk { every { findById(any()) } returns Optional.of(categoria) }
    private val livroRepository: LivroRepository = mockk { every { save(any()) } returns livro }
    private val validaLivroRequest: ValidaLivroRequest = mockk()
    private val cadastraLivroService: CadastraLivroService = CadastraLivroService(
        consultaAutorService,
        categoriaRepository,
        livroRepository,
        validaLivroRequest
    )

    @Test
    // Boa prática para se nomear testes em Kotlin
    fun `Deve cadastrar no livro`() {
        // Posso chamar o every diretamente da função de teste também
        //Mockando método que "retorna" Void (Unit no caso do Kotlin)
        every { validaLivroRequest.valida(any(), any()) } returns Unit

        cadastraLivroService.executa(livroRequest)

        // verificar se foi chamada exatamente 1 vez a função tal
        verify(exactly = 1) { categoriaRepository.findById(any()) }
        verify(exactly = 1) { consultaAutorService.buscaPorId(any()) }
        verify(exactly = 1) { validaLivroRequest.valida(any(), any()) }
        verify(exactly = 1) { livroRepository.save(any()) }
    }

    @Test
    fun `Deve interromper o fluxo ao falhar na validação`() {
        every { validaLivroRequest.valida(any(), any()) } throws CampoDuplicadoException(
            mensagem = "Ja existe um livro com esse titulo", campo = "Título"
        )

        Assertions.assertThrows(CampoDuplicadoException::class.java) { cadastraLivroService.executa(livroRequest) }

        verify(exactly = 1) { validaLivroRequest.valida(any(), any()) }
        // verificar se não foi chamada nenhuma vez a função tal
        verify(exactly = 0) { categoriaRepository.findById(any()) }
        verify(exactly = 0) { consultaAutorService.buscaPorId(any()) }
        verify(exactly = 0) { livroRepository.save(any()) }
    }
}