package io.github.monthalcantara.bibliotecaCDD.emprestimo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.monthalcantara.bibliotecaCDD.livro.LivroEmprestimoRequest;
import io.github.monthalcantara.bibliotecaCDD.usuario.Usuario;
import io.github.monthalcantara.bibliotecaCDD.utils.validation.ExisteRecurso;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoEmprestimo {

    @NotNull
    @JsonProperty(value = "usuario")
    @ExisteRecurso(fieldName = "id", domainClass = Usuario.class, message = "Não existe usuário com o id informado")
    private Integer idUsuario;

    @NotEmpty
    @Size(max = 5)
    @JsonProperty(value = "livros")
    @Valid
    private Set<LivroEmprestimoRequest> livros = new HashSet<>();

    @Deprecated
    private PedidoEmprestimo() {
    }

    public PedidoEmprestimo(@NotNull Integer idUsuario, @NotEmpty @Size(max = 5) Set<LivroEmprestimoRequest> livros) {
        this.idUsuario = idUsuario;
        this.livros = livros;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Set<LivroEmprestimoRequest> getLivros() {
        return livros;
    }
}
