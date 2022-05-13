package io.github.monthalcantara.biblioteca.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Entity
/*
* Adicionado indice (Além do Id) a tabela poderia ser um indice com duas
* ou mais colunas passando as colunas @Index(columnList = "isbn", "titulo")
**/
@Table(name ="Livro", indexes = @Index(columnList = "isbn"))
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @NotNull
    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @NotBlank
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

}
