package io.github.monthalcantara.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
//@DynamoDBTable(tableName = "Cliente")
public class ClienteEntity {

//    @DynamoDBHashKey(attributeName = "cpf")
    private String cpf;

//    @DynamoDBIndexHashKey(attributeName = "email")
    private String email;

//    @DynamoDBAttribute(attributeName = "nome")
    private String nome;

//    @DynamoDBAttribute(attributeName = "sobrenome")
    private String sobrenome;

//    @DynamoDBAttribute(attributeName = "data_de_nascimento")
    private LocalDate dataNascimento;

//    @DynamoDBAttribute(attributeName = "data_de_criacao")
    private LocalDate dataCriacao;

}
