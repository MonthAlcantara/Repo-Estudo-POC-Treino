package io.github.monthalcantara.bibliotecaCDD.livro;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.monthalcantara.bibliotecaCDD.utils.validation.ValorUnico;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class NovoLivroRequest {

    @NotBlank
    @JsonProperty("titulo")
    private String titulo;

    @NotNull
    @JsonProperty("preco")
    private BigDecimal preco;

    @NotBlank
    @ValorUnico(domainClass = Livro.class, fieldName = "isbn", message = "Ja existe um Livro com esse Isbn cadastrado")
    @JsonProperty("isbn")
    private String isbn;

    @Deprecated
    private NovoLivroRequest() {
    }

    public NovoLivroRequest(@NotBlank String titulo, @NotNull BigDecimal preco, @NotBlank String isbn) {
        this.titulo = titulo;
        this.preco = preco;
        this.isbn = isbn;
    }

    public Livro toModel() {
        return new Livro(this.titulo, this.preco, this.isbn);
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
