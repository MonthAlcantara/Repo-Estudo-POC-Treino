package io.github.monthalcantara.biblioteca.processors;

import io.github.monthalcantara.biblioteca.domain.Livro;
import org.springframework.stereotype.Component;

@Component
public class CadastraLivroProcessor implements LivroProcessor{

    @Override
    public Livro process(Livro livro) {
        return livro;
    }
}
