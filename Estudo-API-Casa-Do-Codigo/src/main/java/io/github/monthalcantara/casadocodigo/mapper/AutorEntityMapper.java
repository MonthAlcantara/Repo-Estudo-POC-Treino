package io.github.monthalcantara.casadocodigo.mapper;

import io.github.monthalcantara.casadocodigo.domain.Autor;
import io.github.monthalcantara.casadocodigo.entity.AutorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutorEntityMapper {

    AutorEntityMapper MAPPER = Mappers.getMapper(AutorEntityMapper.class);

    Autor mapFrom(AutorEntity autorEntity);

    AutorEntity mapFrom(Autor autor);

}
