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
public class EnderecoResponse implements Serializable {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;


    public EnderecoResponseDto toDto() {
        return EnderecoResponseDto.builder()
                .bairro(this.bairro)
                .cep(this.cep)
                .cidade(this.localidade)
                .uf(this.uf)
                .logradouro(this.logradouro)
                .build();
    }
}

