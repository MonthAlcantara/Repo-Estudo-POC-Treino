package io.github.monthalcantara.bibliotecaCDD.usuario;


import javax.validation.constraints.NotNull;

public class NovoUsuarioRequest {

    @NotNull
    private String tipoUsuario;

    @Deprecated
    private NovoUsuarioRequest() {
    }

    public NovoUsuarioRequest(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario toModel(){
        TipoUsuario usuario = TipoUsuario.toEnum(this.tipoUsuario);
        return new Usuario(usuario);
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }
}
