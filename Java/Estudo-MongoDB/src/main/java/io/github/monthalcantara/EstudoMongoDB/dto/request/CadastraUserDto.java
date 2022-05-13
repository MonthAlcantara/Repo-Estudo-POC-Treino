package io.github.monthalcantara.EstudoMongoDB.dto.request;

import io.github.monthalcantara.EstudoMongoDB.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collation = "users")
public class CadastraUserDto {

    private String name;

    private String email;

    public User toModel() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }
}
