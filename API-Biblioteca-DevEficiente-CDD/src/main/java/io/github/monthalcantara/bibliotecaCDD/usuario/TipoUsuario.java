package io.github.monthalcantara.bibliotecaCDD.usuario;

import java.util.Objects;

public enum TipoUsuario {

    PADRAO("padrao"), PESQUISADOR("pesquisador");

    private String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoUsuario toEnum(String descricao) {
        if (Objects.isNull(descricao)) {
            return null;
        }
        for (TipoUsuario usuario : values()) {
            if (descricao.equalsIgnoreCase(usuario.getDescricao())) {
                return usuario;
            }
        }
        throw new IllegalArgumentException("A descrição informada inválida: " + descricao);
    }
}
