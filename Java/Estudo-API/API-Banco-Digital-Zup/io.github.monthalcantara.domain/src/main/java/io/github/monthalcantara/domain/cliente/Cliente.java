package io.github.monthalcantara.domain.cliente;

import io.github.monthalcantara.domain.visitor.Visitable;
import io.github.monthalcantara.domain.visitor.Visitor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Cliente implements Visitable<Cliente> {

    private String nome;

    private String sobrenome;

    private String email;

    private LocalDate dataNascimento;

    private String cpf;

    private LocalDate dataCriacao;

    @Override
    public void accept(Visitor<Cliente, ?> element) {
       element.visit(this);
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}

