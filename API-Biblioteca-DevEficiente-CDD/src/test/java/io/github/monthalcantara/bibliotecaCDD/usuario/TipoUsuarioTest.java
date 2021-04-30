package io.github.monthalcantara.bibliotecaCDD.usuario;

import io.github.monthalcantara.bibliotecaCDD.livro.Livro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TipoUsuarioTest {

    private final Livro livro = new Livro("Titulo", BigDecimal.valueOf(42L), "Isbn");

    @Test
    @DisplayName("DEve retornar false para tipos de usuarios que não existem")
    void testExists() {
        assertFalse(TipoUsuario.exists("Descricao"));
        assertFalse(TipoUsuario.exists("foo"));
    }

    @Test
    @DisplayName("Deve retornar que não é possível solicitar emprestimo")
    void naoPodeSolicitarNovoEmprestimoTest() {
        assertFalse(TipoUsuario.PADRAO.podeSolicitarNovoEmprestimo(livro));
        assertFalse(
                TipoUsuario.PESQUISADOR.podeSolicitarNovoEmprestimo(livro));
        assertFalse(TipoUsuario.PESQUISADOR.podeSolicitarNovoEmprestimo(livro));
    }

    @Test
    @DisplayName("Deve retornar que  é possível solicitar emprestimo")
    void podeSolicitarNovoEmprestimoTest() {
        livro.setDisponivel(true);
        assertTrue(TipoUsuario.PADRAO.podeSolicitarNovoEmprestimo(livro));
    }

    @Test
    @DisplayName("Não deve converter enum se não existe")
    void toEnumSucessoTest() {
        assertNull(TipoUsuario.toEnum("Descricao"));
    }

    @Test
    @DisplayName("Deve converter enum se existe")
    void toEnumFalhaTest() {
        assertNotNull(TipoUsuario.toEnum("padrao"));
    }


    @Test
    @DisplayName("Deve encontrar usuario por um tipo existentee")
    void encontraTipoUsuarioTest() {
        assertEquals(TipoUsuario.PADRAO, TipoUsuario.valueOf("PADRAO"));
    }

}

