package io.github.monthalcantara.bibliotecaCDD.livro;

import io.github.monthalcantara.bibliotecaCDD.usuario.UsuarioController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("v1/livros")
public class LivroController {

    private Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    private LivroRepository livroRepository;

    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastraNovoLivro(@RequestBody @Valid NovoLivroRequest livroRequest){
        logger.info("Recebida requisição para criação de novo livro");

        Livro livro = livroRequest.toModel();

        logger.info("Realizada conversão de Livrorequest para  livro");

        Livro livroSalvo = livroRepository.save(livro);

        logger.info("Livro salvo no banco de dados");

        LivroResponse livroResponse = livroSalvo.toResponse();

        logger.info("Realizada conversão de Livro salvo para  LivroResponse");

        return new ResponseEntity(livroResponse, HttpStatus.CREATED);
    }
}
