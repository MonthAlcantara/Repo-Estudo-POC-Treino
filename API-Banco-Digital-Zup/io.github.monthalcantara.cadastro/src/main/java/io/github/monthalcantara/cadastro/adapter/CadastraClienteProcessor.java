package io.github.monthalcantara.cadastro.adapter;

import io.github.monthalcantara.cadastro.visitor.AdicionaDataCriacaoVisitor;
import io.github.monthalcantara.core.command.Command;
import io.github.monthalcantara.core.command.CommandContext;
import io.github.monthalcanta.repository.adapter.ClientePersistence;
import io.github.monthalcantara.domain.cliente.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CadastraClienteProcessor implements Command<Cliente> {

    @Autowired
    private AdicionaDataCriacaoVisitor adicionaDataCriacaoVisitor;

    @Autowired
    private ClientePersistence clientePersistence;

    @Override
    public Cliente process(CommandContext commandContext) {

        log.info("Iniciando processo de cadastro do cliente");

        Cliente cliente = (Cliente) commandContext.getData(commandContext);

        log.info("Recuperado valor do Command Context");

        cliente.accept(adicionaDataCriacaoVisitor);

        log.info("Finalizado processo de cadastro do cliente");

        //clientePersistence.save(cliente);

        return cliente;
    }
}
