package io.github.monthalcantara.cadastro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/clientes")
public class ClienteController {

    @GetMapping
    public ResponseEntity cria(){
        return ResponseEntity.ok("chegou");
    }
}
