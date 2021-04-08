package io.github.monthalcantara.bibliotecaCDD.livro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.monthalcantara.bibliotecaCDD.utils.validation.ExisteRecurso;

import javax.validation.constraints.NotNull;

public class LivroEmprestimoRequest {

    @NotNull(message = "Necessario informar o id do livro")
    @ExisteRecurso(domainClass = Livro.class, fieldName = "id", message =  "Não existe livro com o id informado")
    @JsonProperty("livro")
    private Integer idLivro;

    @Deprecated
    private LivroEmprestimoRequest() {
    }

    public LivroEmprestimoRequest(@NotNull(message = "Necessario informar o id do livro") Integer idLivro) {
        this.idLivro = idLivro;
    }

    public Integer getIdLivro() {
        return idLivro;
    }
}
