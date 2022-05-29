create table role(
    id bigint not null auto_increment,
    nome varchar(50) not null,
    PRIMARY KEY (id)
);

INSERT INTO role (id, nome) VALUES (1, 'LEITURA_ESCRITA');
INSERT INTO role (id, nome) VALUES (2, 'LEITURA');
