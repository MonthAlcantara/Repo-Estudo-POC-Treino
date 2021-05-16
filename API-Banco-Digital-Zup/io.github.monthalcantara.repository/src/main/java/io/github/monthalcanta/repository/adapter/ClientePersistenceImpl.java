package io.github.monthalcanta.repository.adapter;

import io.github.monthalcantara.core.persistence.ClientePersistence;
import io.github.monthalcantara.domain.cliente.Cliente;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public class ClientePersistenceImpl implements ClientePersistence {

//    private final ClienteEntityMapper INSTANCE = Mappers.getMapper(ClienteEntityMapper.class);

    @Override
    public Cliente save(Cliente object) {
//        ClienteEntity clienteEntity = INSTANCE.mapFrom(object);
//        // dynamoDBMapper.save(clienteEntity);
        return object;
    }

    @Override
    public Cliente update(Cliente object) {
        return null;
    }

    @Override
    public Cliente find(Cliente object) {
        return null;
    }

    @Override
    public Cliente findById(String id) {
        return null;
    }
}
