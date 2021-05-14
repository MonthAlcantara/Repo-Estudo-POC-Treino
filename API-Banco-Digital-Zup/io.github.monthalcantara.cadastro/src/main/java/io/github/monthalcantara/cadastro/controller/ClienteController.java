package io.github.monthalcantara.cadastro.controller;

import io.github.monthalcantara.cadastro.dto.NovoClienteRequest;
import io.github.monthalcantara.cadastro.mapper.ClienteMapper;
import io.github.monthalcantara.domain.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/clientes")
public class ClienteController {

    private final ClienteMapper INSTANCE;

    @Autowired
    public ClienteController(ClienteMapper instance) {
        INSTANCE = instance;
    }

    @PostMapping
    public ResponseEntity cria(@RequestBody NovoClienteRequest novoClienteRequest){
        Cliente cliente = INSTANCE.mapFrom(novoClienteRequest);
        return ResponseEntity.ok(cliente);
    }
}
