package io.github.monthalcantara.cadastro.processor;

import io.github.monthalcantara.core.command.CommandContext;
import io.github.monthalcantara.core.processor.CadastraClienteProcessor;
import io.github.monthalcantara.domain.cliente.Cliente;
import org.springframework.stereotype.Service;

@Service
public class CadastraClienteProcessorImpl implements CadastraClienteProcessor {

    @Override
    public Cliente process(CommandContext commandContext) {
        Cliente data = (Cliente) commandContext.getData(commandContext);

        return data;
    }
}
