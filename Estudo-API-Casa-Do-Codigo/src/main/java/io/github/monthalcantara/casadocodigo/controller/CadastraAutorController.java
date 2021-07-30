package io.github.monthalcantara.casadocodigo.controller;

import io.github.monthalcantara.casadocodigo.domain.Autor;
import io.github.monthalcantara.casadocodigo.dto.request.AutorDto;
import io.github.monthalcantara.casadocodigo.dto.response.AutorResponseDto;
import io.github.monthalcantara.casadocodigo.repository.AutorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("v1/autores")
public class CadastraAutorController {

    @Autowired
    private AutorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastra(@Valid @RequestBody AutorDto cadastraAutorRequest) {

        log.info("Recebida solicitação cadastro de autor");

        Autor autor = cadastraAutorRequest.toDomain();

        log.info("Convertido request para Autor");

        Autor autorSalvo = repository.save(autor);

        log.info("Finalizado processamento de autor");

        AutorResponseDto autorResponse = autorSalvo.toResponse();

        log.info("Convertido autor para objeto de response");

        return ResponseEntity.ok(autorResponse);

    }
}
