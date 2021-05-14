package io.github.monthalcantara.cadastro.processor;

import io.github.monthalcantara.core.processor.CadastraClienteProcessor;
import io.github.monthalcantara.domain.cliente.Cliente;
import org.springframework.stereotype.Service;

@Service
public class CadastraClienteProcessorImpl implements CadastraClienteProcessor<Cliente> {

   @Override
    public Cliente process(Cliente cliente) {
        return null;
    }
}
