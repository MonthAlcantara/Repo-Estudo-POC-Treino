CREATE TABLE livro(
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo varchar(50) not null unique,
    resumo varchar(500) not null,
    sumario varchar(100) not null,
    preco BIGINT NOT NULL,
    numero_paginas BIGINT NOT NULL,
    isbn varchar(50) not null unique,
    categoria_id BIGINT NOT NULL,
    autor_id BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(categoria_id) REFERENCES categorias (id),
    FOREIGN KEY(autor_id) REFERENCES autores (id)
);
