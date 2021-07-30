package io.github.monthalcantara.casadocodigo.domain;

import io.github.monthalcantara.casadocodigo.dto.response.AutorResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nome;

    private String descricao;

    private LocalDate dataCriacao;

    public AutorResponseDto toResponse() {
        return AutorResponseDto.builder()
                .dataCriacao(this.dataCriacao)
                .email(this.email)
                .nome(this.nome)
                .descricao(this.descricao)
                .build();
    }
}
