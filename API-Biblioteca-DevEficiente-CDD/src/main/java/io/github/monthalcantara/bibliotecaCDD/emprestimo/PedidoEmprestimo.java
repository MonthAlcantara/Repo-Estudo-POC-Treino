package io.github.monthalcantara.bibliotecaCDD.emprestimo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.monthalcantara.bibliotecaCDD.livro.Livro;
import io.github.monthalcantara.bibliotecaCDD.usuario.Usuario;
import io.github.monthalcantara.bibliotecaCDD.utils.validation.ExisteRecurso;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoEmprestimo {

    @NotNull
    @JsonProperty(value = "usuario")
    @ExisteRecurso(fieldName = "id", domainClass = Usuario.class, message = "Não existe usuário com o id informado")
    private Integer idUsuario;

    @NotNull(message = "Necessario informar o id do livro")
    @ExisteRecurso(domainClass = Livro.class, fieldName = "id", message =  "Não existe livro com o id informado")
    @JsonProperty("livro")
    private Integer idLivro;
    @Deprecated
    private PedidoEmprestimo() {
    }

    public PedidoEmprestimo(@NotNull Integer idUsuario, @NotNull Integer livro) {
        this.idUsuario = idUsuario;
        this.idLivro = livro;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Integer getIdLivro() {
        return idLivro;
    }
}
