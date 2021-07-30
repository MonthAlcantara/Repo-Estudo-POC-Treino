package io.github.monthalcantara.casadocodigo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class AutorResponseDto {

    private String email;

    private String nome;

    private String descricao;

    private LocalDate dataCriacao;

}
