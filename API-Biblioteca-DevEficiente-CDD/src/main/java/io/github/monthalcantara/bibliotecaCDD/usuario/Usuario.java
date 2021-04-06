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

    @NotNull
    @Column(name = "quantidade_livros_emprestados", nullable = false)
    private int qtdLivrosEmprestados;

    @Deprecated
    private Usuario() {
    }

    public Usuario( @NotNull TipoUsuario tipoUsuario) {
        this.qtdLivrosEmprestados = 0;
        this.tipoUsuario = tipoUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public String getDescricaoTipoUsuario() {
        return tipoUsuario.getDescricao();
    }

    public int getQtdLivrosEmprestados() {
        return qtdLivrosEmprestados;
    }

    public Integer getId() {
        return id;
    }
}
