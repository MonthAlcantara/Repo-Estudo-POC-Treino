package io.github.monthalcantara.domain.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Cliente {

    private String nome;

    private String sobrenome;

    private String email;

    private LocalDate dataNascimento;

    private String cpf;

    private LocalDate dataCriacao;
}

