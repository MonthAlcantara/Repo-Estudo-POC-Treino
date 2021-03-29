package io.github.monthalcantara.biblioteca.repository;

import io.github.monthalcantara.biblioteca.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro,Integer> {
}
