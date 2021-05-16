package io.github.monthalcanta.repository.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@DynamoDBTable(tableName = "Cliente")
public class ClienteEntity {

    @DynamoDBHashKey(attributeName = "cpf")
    private String cpf;

    @DynamoDBIndexHashKey(attributeName = "email")
    private String email;

    @DynamoDBAttribute(attributeName = "nome")
    private String nome;

    @DynamoDBAttribute(attributeName = "sobrenome")
    private String sobrenome;

    @DynamoDBAttribute(attributeName = "data_de_nascimento")
    private LocalDate dataNascimento;

    @DynamoDBAttribute(attributeName = "data_de_criacao")
    private LocalDate dataCriacao;

}
