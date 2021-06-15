package io.github.monthalcantara.casadocodigo.core;

import io.github.monthalcantara.casadocodigo.domain.Autor;

public interface CadastraAutorCommandProcessor {

    Autor process(Autor autor);
}
