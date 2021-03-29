package io.github.monthalcantara.bibliotecaCDD.livro;

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

    private LivroRepository livroRepository;

    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastraNovoLivro(@RequestBody @Valid NovoLivroRequest livroRequest){

        Livro livro = livroRequest.toModel();

        Livro livroSalvo = livroRepository.save(livro);

        return ResponseEntity.ok(livroSalvo.toResponse());
    }
}
