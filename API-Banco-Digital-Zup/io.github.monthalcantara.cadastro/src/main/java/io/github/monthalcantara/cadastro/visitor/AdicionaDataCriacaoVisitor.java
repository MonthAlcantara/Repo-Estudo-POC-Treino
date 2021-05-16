package io.github.monthalcantara.cadastro.visitor;

import io.github.monthalcantara.domain.visitor.Visitor;
import io.github.monthalcantara.domain.cliente.Cliente;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AdicionaDataCriacaoVisitor implements Visitor<Cliente, Void> {

    @Override
    public Void visit(Cliente cliente) {
        cliente.setDataCriacao(LocalDate.now());
        return null;
    }
}
