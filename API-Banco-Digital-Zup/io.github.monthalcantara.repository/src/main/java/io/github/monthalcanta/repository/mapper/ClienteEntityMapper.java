package io.github.monthalcanta.repository.mapper;

import io.github.monthalcanta.repository.model.ClienteEntity;
import io.github.monthalcantara.domain.cliente.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteEntityMapper {

    ClienteEntity mapFrom(Cliente cliente);

}
