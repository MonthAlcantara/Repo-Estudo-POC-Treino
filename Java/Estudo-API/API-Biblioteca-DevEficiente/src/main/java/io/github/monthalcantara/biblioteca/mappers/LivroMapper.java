package io.github.monthalcantara.biblioteca.mappers;


import io.github.monthalcantara.biblioteca.domain.Livro;
import io.github.monthalcantara.biblioteca.dto.request.LivroRequest;
import io.github.monthalcantara.biblioteca.dto.response.LivroResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    Livro mapFrom(LivroRequest livroRequest);

    LivroResponse mapFrom(Livro livro);
}
