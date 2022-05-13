package io.github.monthalcantara.biblioteca.controller;

import io.github.monthalcantara.biblioteca.domain.Livro;
import io.github.monthalcantara.biblioteca.dto.request.LivroRequest;
import io.github.monthalcantara.biblioteca.dto.response.LivroResponse;
import io.github.monthalcantara.biblioteca.mappers.LivroMapper;
import io.github.monthalcantara.biblioteca.processors.CadastraLivroProcessor;
import io.github.monthalcantara.biblioteca.processors.LivroProcessor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("v1/livros")
public class LivroController {

    private static Logger logger = LoggerFactory.getLogger(LivroController.class);
    private LivroMapper INSTANCE = Mappers.getMapper(LivroMapper.class);
    private CadastraLivroProcessor cadastraLivroProcessor;

    public LivroController(CadastraLivroProcessor cadastraLivroProcessor) {
        this.cadastraLivroProcessor = cadastraLivroProcessor;
    }

    @PostMapping
    public ResponseEntity criaNovoLivro(@RequestBody @Valid LivroRequest livroRequest, UriComponentsBuilder builder){
        logger.debug("","Recebida LivroRequest", livroRequest);

        logger.debug("Convertendo LivroRequest em Livro", livroRequest);

        Livro livro = INSTANCE.mapFrom(livroRequest);

        logger.debug("","Iniciando Processamento ", livroRequest);

        Livro livroSalvo = cadastraLivroProcessor.process(livro);

        logger.debug("Montando o Location do Livro");

        URI uri = builder.path("/livros/{id}").buildAndExpand(livroSalvo.getId()).toUri();

        logger.debug("Convertendo livro salvo para Response");

        LivroResponse livroResponse = INSTANCE.mapFrom(livroSalvo);

        logger.debug("", "Objeto convertindo salvo para Response", livroResponse);

        return ResponseEntity.created(uri).body(livroResponse);
    }


}
