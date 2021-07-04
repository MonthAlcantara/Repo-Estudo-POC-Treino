package io.github.monthalcantara.casadocodigo.processor;

import io.github.monthalcantara.casadocodigo.core.CadastraAutorCommandProcessor;
import io.github.monthalcantara.casadocodigo.domain.Autor;
import io.github.monthalcantara.casadocodigo.entity.AutorEntity;
import io.github.monthalcantara.casadocodigo.mapper.AutorEntityMapper;
import io.github.monthalcantara.casadocodigo.mapper.AutorMapper;
import io.github.monthalcantara.casadocodigo.repository.AutorRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class AutorCadastraAutorCommandProcessorImpl implements CadastraAutorCommandProcessor {

    private final AutorRepository autorRepository;
    private final AutorEntityMapper MAPPER = Mappers.getMapper(AutorEntityMapper.class);


    public AutorCadastraAutorCommandProcessorImpl(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public Autor process(Autor autor) {

        final AutorEntity autorEntity = MAPPER.mapFrom(autor);

        final AutorEntity autorSalvo = autorRepository.save(autorEntity);

        final Autor processaAutor = MAPPER.mapFrom(autorSalvo);

        return processaAutor;
    }
}
