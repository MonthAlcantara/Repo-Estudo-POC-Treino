package io.github.monthalcantara.repository.adapter;

import io.github.monthalcantara.repository.model.ClienteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientePersistence extends CrudRepository<ClienteEntity, String> {
}
