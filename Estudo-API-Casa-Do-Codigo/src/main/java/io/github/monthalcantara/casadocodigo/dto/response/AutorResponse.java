package io.github.monthalcantara.casadocodigo.dto.response;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AutorResponse {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public AutorResponse(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}

