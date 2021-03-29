package io.github.monthalcantara.bibliotecaCDD.livro;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.monthalcantara.bibliotecaCDD.utils.validation.ValorUnico;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class LivroResponse {

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("preco")
    private BigDecimal preco;

    @JsonProperty("isbn")
    private String isbn;

    @Deprecated
    private LivroResponse() {
    }

    public LivroResponse(@NotBlank String titulo, @NotNull BigDecimal preco, @NotBlank String isbn) {
        this.titulo = titulo;
        this.preco = preco;
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getIsbn() {
        return isbn;
    }
}
