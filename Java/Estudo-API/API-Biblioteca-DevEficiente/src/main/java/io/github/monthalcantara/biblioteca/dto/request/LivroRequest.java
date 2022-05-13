package io.github.monthalcantara.biblioteca.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class LivroRequest {

    @NotBlank
    @JsonProperty("titulo")
    private String titulo;

    @NotNull
    @Positive
    @JsonProperty("preco")
    private BigDecimal preco;

    @NotBlank
    @JsonProperty("isbn")
    private String isbn;

    @Deprecated
    private LivroRequest() {
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
