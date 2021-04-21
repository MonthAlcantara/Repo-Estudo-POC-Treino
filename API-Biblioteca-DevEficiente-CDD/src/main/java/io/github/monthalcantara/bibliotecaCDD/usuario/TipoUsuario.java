package io.github.monthalcantara.bibliotecaCDD.usuario;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum TipoUsuario {

    PADRAO("padrao"), PESQUISADOR("pesquisador");

    private String descricao;

    private static final Map<String, TipoUsuario> mapString = new HashMap<>();

    static {
        for (TipoUsuario tipo : TipoUsuario.values()) {
            mapString.put(tipo.descricao, tipo);
        }

    }

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoUsuario toEnum(String descricao) {
        return Objects.nonNull(descricao) ? mapString.get(descricao.toLowerCase()) : null;
    }

    public static Boolean exists(String descricao) {
        return mapString.containsKey(descricao.toLowerCase());
    }
}
