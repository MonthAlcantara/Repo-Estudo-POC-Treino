package io.github.monthalcantara.cadastro.mapper;

import io.github.monthalcantara.cadastro.dto.request.NovoClienteRequest;
import io.github.monthalcantara.cadastro.dto.response.ClienteResponse;
import io.github.monthalcantara.domain.cliente.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente mapFrom(NovoClienteRequest request);

    ClienteResponse mapFrom(Cliente cliente);
}
