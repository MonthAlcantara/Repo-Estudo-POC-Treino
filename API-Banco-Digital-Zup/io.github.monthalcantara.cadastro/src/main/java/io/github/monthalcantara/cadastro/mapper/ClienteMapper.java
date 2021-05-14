package io.github.monthalcantara.cadastro.mapper;

import io.github.monthalcantara.cadastro.dto.NovoClienteRequest;
import io.github.monthalcantara.domain.cliente.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class ClienteMapper {

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "sobrenome", source = "sobrenome")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "dataNascimento", source = "dataNascimento")
    @Mapping(target = "cpf", source = "cpf")
    @Mapping(target = "dataCriacao", source = "dataCriacao")
    abstract Cliente mapFrom(NovoClienteRequest request);
}
