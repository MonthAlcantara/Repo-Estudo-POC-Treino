package io.github.monthalcantara.casadocodigo.processor;

import io.github.monthalcantara.casadocodigo.core.CadastraAutorCommandProcessor;
import io.github.monthalcantara.casadocodigo.domain.Autor;
import org.springframework.stereotype.Component;

@Component
public class AutorCadastraAutorCommandProcessorImpl implements CadastraAutorCommandProcessor {

    @Override
    public Autor process(Autor autor) {
        final Autor processaAutor = new Autor("fulano","teste@gmail.com","descricao");

        return processaAutor;
    }
}
