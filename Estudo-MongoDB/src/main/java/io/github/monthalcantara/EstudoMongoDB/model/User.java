package io.github.monthalcantara.EstudoMongoDB.model;

import io.github.monthalcantara.EstudoMongoDB.dto.response.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@Document(collation = "users")
public class User {
    @Id
    private String id;

    private String name;

    private String email;

    public UserDto toResponse() {
        return UserDto.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }
}
