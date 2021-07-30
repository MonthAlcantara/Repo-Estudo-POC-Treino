package io.github.monthalcantara.casadocodigo.dto.request;

import io.github.monthalcantara.casadocodigo.domain.Autor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AutorDto {

    private String email;

    private String nome;

    private String descricao;


    public Autor toDomain() {
        return Autor.builder()
                .dataCriacao(LocalDate.now())
                .descricao(this.descricao)
                .nome(this.nome)
                .email(this.email)
                .build();
    }
}
