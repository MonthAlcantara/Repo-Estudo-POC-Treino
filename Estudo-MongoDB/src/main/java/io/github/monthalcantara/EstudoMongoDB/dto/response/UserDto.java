package io.github.monthalcantara.EstudoMongoDB.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@Document(collation = "users")
public class UserDto {

    private String name;

    private String email;
}
