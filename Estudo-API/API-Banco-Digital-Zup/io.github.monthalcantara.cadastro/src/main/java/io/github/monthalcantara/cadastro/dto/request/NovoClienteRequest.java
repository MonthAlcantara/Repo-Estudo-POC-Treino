package io.github.monthalcantara.cadastro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NovoClienteRequest {

    private String nome;

    private String sobrenome;

    private String email;

    private LocalDate dataNascimento;

    private String cpf;

    private LocalDate dataCriacao;
}
