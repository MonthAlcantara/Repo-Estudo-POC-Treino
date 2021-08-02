package io.github.monthalcantara.EstudoMongoDB.repository;

import io.github.monthalcantara.EstudoMongoDB.model.Todo;
import io.github.monthalcantara.EstudoMongoDB.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * É a mesma ideia do JpaRepository. Única coisa que muda é o tipo de banco
 * O repository estende de MongoRepository e herda seus métodos.
 * Posso também usar a anotação @Query e passa uma pesquisa mais personalizada
 * @Query("{name: ?0}") <- ?0 seria o primeiro parametro (nesse caso único tbm)
 * @Query(value = "Posso escrever uma query tbm")
 */
@RepositoryRestResource(collectionResourceRel = "todos", path = "todos")
public interface TodoRepository extends CrudRepository<Todo, String> {

}
