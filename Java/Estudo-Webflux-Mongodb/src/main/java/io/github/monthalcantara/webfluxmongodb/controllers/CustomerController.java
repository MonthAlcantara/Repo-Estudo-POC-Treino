package io.github.monthalcantara.webfluxmongodb.controllers;

import io.github.monthalcantara.webfluxmongodb.models.Customer;
import io.github.monthalcantara.webfluxmongodb.repositories.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    /**
     * Como eu estou querendo retornar mais de um elemento, eu uso o Flux
     *
     * @return ResponseEntity<Flux < Customer>>
     */
    @GetMapping
    public ResponseEntity<Flux<Customer>> findAll() {
        /* Como eu estou usando o ReactiveCrudRepository, eu ja tenho o retorno de um flux
         caso eu estivesse usando um repository normal, eu teria q encapsular num Flux
        */
        return ResponseEntity.ok(repository.findAll());
    }

}
