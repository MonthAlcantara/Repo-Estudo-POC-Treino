package io.github.monthalcanta.repository.adapter;

import io.github.monthalcanta.repository.model.ClienteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientePersistence extends CrudRepository<ClienteEntity, String> {
}
