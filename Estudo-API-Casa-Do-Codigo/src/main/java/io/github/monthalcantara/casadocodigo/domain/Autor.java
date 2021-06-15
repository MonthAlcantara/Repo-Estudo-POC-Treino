package io.github.monthalcantara.casadocodigo.domain;


import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class Autor {

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String descricao;

    private LocalDateTime instanteCriacao;

    public Autor(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.instanteCriacao = LocalDateTime.now();
    }
}
