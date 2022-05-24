create table usuarios(
    id bigint not null auto_increment,
    username varchar(50) not null,
    password varchar(100) not null,
    primary key(id)
);

create table autores(
    id bigint not null auto_increment,
    email varchar(50) not null,
    nome varchar(100) not null,
    descricao varchar(100) not null,
    instante_criacao datetime not null,
    primary key(id)
);