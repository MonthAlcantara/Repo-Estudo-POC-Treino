package io.github.monthalcantara.casadocodigo.repository;

import io.github.monthalcantara.casadocodigo.entity.AutorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@EnableScan
@Repository
public interface AutorRepository extends CrudRepository<AutorEntity, Long> {

    Optional<AutorEntity> findById(Long id);
    List<AutorEntity> findAll();
}
