package io.github.monthalcantara.cadastro.visitor;

import io.github.monthalcantara.core.visitor.Visitor;
import io.github.monthalcantara.domain.cliente.Cliente;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AdicionadaDataCriacaoVisitor implements Visitor<Cliente, Void> {

    @Override
    public Void visit(Cliente cliente) {
        cliente.setDataCriacao(LocalDate.now());
        return null;
    }
}
