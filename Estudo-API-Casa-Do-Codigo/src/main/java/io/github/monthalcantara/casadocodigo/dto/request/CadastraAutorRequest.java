package io.github.monthalcantara.casadocodigo.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CadastraAutorRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String descricao;
}
