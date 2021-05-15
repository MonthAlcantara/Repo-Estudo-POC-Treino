package io.github.monthalcantara.cadastro.processor;

import io.github.monthalcantara.cadastro.visitor.AdicionadaDataCriacaoVisitor;
import io.github.monthalcantara.core.command.CommandContext;
import io.github.monthalcantara.domain.cliente.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CadastraClienteProcessorImpl implements CadastraClienteProcessor {

    private final AdicionadaDataCriacaoVisitor adicionadaDataCriacaoVisitor;

    public CadastraClienteProcessorImpl(AdicionadaDataCriacaoVisitor adicionadaDataCriacaoVisitor) {
        this.adicionadaDataCriacaoVisitor = adicionadaDataCriacaoVisitor;
    }

    @Override
    public Cliente process(CommandContext commandContext) {

        log.info("Iniciando processo de cadastro do cliente");

        Cliente cliente = (Cliente) commandContext.getData(commandContext);

        log.info("Recuperado valor do Command Context");

        cliente.accept(adicionadaDataCriacaoVisitor);

        log.info("Finalizado processo de cadastro do cliente");

        return cliente;
    }
}
