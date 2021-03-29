package io.github.monthalcantara.bibliotecaCDD.livro;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "livro", indexes = @Index(columnList = "isbn", unique = true))
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name="titulo",nullable = false)
    private String titulo;

    @NotNull
    @Column(name="preco",nullable = false)
    private BigDecimal preco;

    @NotBlank
    @Column(name="isbn",nullable = false, unique = true)
    private String isbn;

    @Deprecated
    private Livro() {
    }

    public Livro(@NotBlank String titulo, @NotNull BigDecimal preco, @NotBlank String isbn) {
        this.titulo = titulo;
        this.preco = preco;
        this.isbn = isbn;
    }

    public LivroResponse toResponse() {
        return new LivroResponse(this.titulo,this.preco,this.isbn);
    }
}
