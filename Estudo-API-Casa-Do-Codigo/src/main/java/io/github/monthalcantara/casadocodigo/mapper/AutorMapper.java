package io.github.monthalcantara.casadocodigo.mapper;

import io.github.monthalcantara.casadocodigo.domain.Autor;
import io.github.monthalcantara.casadocodigo.dto.request.CadastraAutorRequest;
import io.github.monthalcantara.casadocodigo.dto.response.AutorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutorMapper {

    AutorMapper MAPPER = Mappers.getMapper(AutorMapper.class);

    Autor mapFrom(CadastraAutorRequest cadastraAutorRequest);

    AutorResponse mapFrom(Autor autor);

}
