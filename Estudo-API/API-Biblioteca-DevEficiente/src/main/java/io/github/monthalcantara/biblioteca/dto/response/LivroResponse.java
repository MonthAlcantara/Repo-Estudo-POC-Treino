package io.github.monthalcantara.biblioteca.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LivroResponse {


    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("preco")
    private BigDecimal preco;

    @JsonProperty("isbn")
    private String isbn;

    @Deprecated
    private LivroResponse() {
    }

    @Override
    public String toString() {
        return "Livro {" +
                "titulo='" + titulo + '\'' +
                ", preco=" + preco +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
