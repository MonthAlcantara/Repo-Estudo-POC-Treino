package io.github.monthalcantara.repository.adapter;

import io.github.monthalcantara.repository.model.ClienteEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface ClientePersistence extends CrudRepository<ClienteEntity, String> {
}
