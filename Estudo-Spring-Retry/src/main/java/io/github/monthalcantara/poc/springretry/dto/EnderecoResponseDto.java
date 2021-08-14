package io.github.monthalcantara.poc.springretry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponseDto implements Serializable {

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
}

