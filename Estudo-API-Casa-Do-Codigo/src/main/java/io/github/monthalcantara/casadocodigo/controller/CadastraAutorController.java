package io.github.monthalcantara.casadocodigo.controller;

import io.github.monthalcantara.casadocodigo.core.CadastraAutorCommandProcessor;
import io.github.monthalcantara.casadocodigo.domain.Autor;
import io.github.monthalcantara.casadocodigo.dto.request.CadastraAutorRequest;
import io.github.monthalcantara.casadocodigo.dto.response.AutorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static io.github.monthalcantara.casadocodigo.mapper.AutorMapper.MAPPER;

@Slf4j
@RestController
@RequestMapping("v1/autores")
public class CadastraAutorController {

    private final CadastraAutorCommandProcessor cadastraAutorProcessor;

    @Autowired
    public CadastraAutorController(CadastraAutorCommandProcessor cadastraAutorProcessor) {
        this.cadastraAutorProcessor = cadastraAutorProcessor;
    }

    @PostMapping
    public ResponseEntity cadastra(@Valid @RequestBody CadastraAutorRequest cadastraAutorRequest) {

        log.info("Recebida solicitação cadastro de autor");

        Autor autor = MAPPER.mapFrom(cadastraAutorRequest);

        log.info("Convertido request para Autor");

        Autor autorProcessado = cadastraAutorProcessor.process(autor);

        log.info("Finalizado processamento de autor");

        AutorResponse autorResponse = MAPPER.mapFrom(autorProcessado);

        log.info("Convertido autor para objeto de response");

        return ResponseEntity.ok(autorResponse);

    }
}
