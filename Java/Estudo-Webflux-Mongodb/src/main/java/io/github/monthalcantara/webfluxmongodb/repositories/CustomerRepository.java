package io.github.monthalcantara.webfluxmongodb.repositories;

import io.github.monthalcantara.webfluxmongodb.models.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, String> {
}
