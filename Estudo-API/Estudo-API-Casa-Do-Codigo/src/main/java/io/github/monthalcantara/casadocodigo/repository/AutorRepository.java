package io.github.monthalcantara.casadocodigo.repository;

import io.github.monthalcantara.casadocodigo.domain.Autor;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface AutorRepository extends Repository <Autor, Long>{

    Autor save(Autor autor);
}
