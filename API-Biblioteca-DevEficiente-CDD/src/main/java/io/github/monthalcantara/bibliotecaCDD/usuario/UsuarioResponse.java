package io.github.monthalcantara.bibliotecaCDD.usuario;


public class UsuarioResponse {

    private Integer id;

    private String tipoUsuario;

    private int qtdLivrosEmprestados;

    @Deprecated
    private UsuarioResponse() {
    }

    public UsuarioResponse(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.tipoUsuario = usuario.getDescricaoTipoUsuario();
        this.qtdLivrosEmprestados = usuario.getQtdLivrosEmprestados();
    }

    public int getQtdLivrosEmprestados() {
        return qtdLivrosEmprestados;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public Integer getId() {
        return id;
    }
}
