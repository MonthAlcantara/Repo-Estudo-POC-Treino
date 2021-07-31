package io.github.monthalcantara.repository.mapper;

import io.github.monthalcantara.repository.model.ClienteEntity;
import io.github.monthalcantara.domain.cliente.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteEntityMapper {

    ClienteEntity mapFrom(Cliente cliente);

}
