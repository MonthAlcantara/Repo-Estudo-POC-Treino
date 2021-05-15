package io.github.monthalcantara.cadastro.controller;

import io.github.monthalcantara.cadastro.dto.request.NovoClienteRequest;
import io.github.monthalcantara.cadastro.dto.response.ClienteResponse;
import io.github.monthalcantara.cadastro.mapper.ClienteMapper;
import io.github.monthalcantara.core.command.CommandContext;
import io.github.monthalcantara.core.processor.CadastraClienteProcessor;
import io.github.monthalcantara.domain.cliente.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1/clientes")
public class ClienteController {

    private final ClienteMapper INSTANCE;
    private final CadastraClienteProcessor cadastraClienteProcessor;

    @Autowired
    public ClienteController(ClienteMapper instance, CadastraClienteProcessor cadastraClienteProcessor) {
        INSTANCE = instance;
        this.cadastraClienteProcessor = cadastraClienteProcessor;
    }

    @PostMapping
    public ResponseEntity cria(@RequestBody NovoClienteRequest novoClienteRequest) {

        log.info("Recebido request para cadastro de novo cliente");

        Cliente cliente = INSTANCE.mapFrom(novoClienteRequest);

        log.info("Realizado mapeamento de Cliente Request para Cliente");

        final CommandContext commandContext = new CommandContext(cliente);
        cadastraClienteProcessor.process(commandContext);

        log.info("Processado pedido de cadastro do Cliente");

        ClienteResponse clienteResponse = INSTANCE.mapFromCliente(cliente);

        log.info("Mapeado cliente processado para response");

        return ResponseEntity.ok(clienteResponse);
    }
}
