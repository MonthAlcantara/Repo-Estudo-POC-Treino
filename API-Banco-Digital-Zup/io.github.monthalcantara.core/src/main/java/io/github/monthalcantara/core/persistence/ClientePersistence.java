package io.github.monthalcantara.core.persistence;

import io.github.monthalcantara.domain.cliente.Cliente;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

@EnableScan
public interface ClientePersistence extends DomainPersistence<Cliente>  {
}
