package io.github.monthalcantara.EstudoMongoDB.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document(collation = "todos")
public class Todo {

    @Id
    private String id;
    private String title;
}
