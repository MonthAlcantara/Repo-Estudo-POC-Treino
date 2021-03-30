package io.github.monthalcantara.bibliotecaCDD.usuario;


public class UsuarioResponse {

    private String tipoUsuario;

    @Deprecated
    private UsuarioResponse() {
    }

    public UsuarioResponse(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public UsuarioResponse(Usuario usuario) {
        this.tipoUsuario = usuario.getDescricaoTipoUsuario();
    }


    public String getTipoUsuario() {
        return tipoUsuario;
    }
}
