package io.github.monthalcantara.bibliotecaCDD.usuario;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    @Deprecated
    private Usuario() {
    }

    public Usuario( @NotNull TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public String getDescricaoTipoUsuario() {
        return tipoUsuario.getDescricao();
    }
}
