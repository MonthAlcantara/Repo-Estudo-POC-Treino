package io.github.monthalcantara.bibliotecaCDD.usuario;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum TipoUsuario {

    PADRAO("padrao"){
        @Override
        public Boolean precisaDefinirPrazo() {
            return true;
        }

        @Override
        public Boolean temLimiteEmprestimos() {
            return true;
        }

        @Override
        public Boolean podePegarRestritos() {
            return false;
        }
    }, PESQUISADOR("pesquisador"){
        @Override
        public Boolean precisaDefinirPrazo() {
            return false;
        }

        @Override
        public Boolean temLimiteEmprestimos() {
            return false;
        }

        @Override
        public Boolean podePegarRestritos() {
            return true;
        }
    };

    private String descricao;

    private static final Map<String, TipoUsuario> mapString = new HashMap<>();

    /*  Bloco estatico - executado somente uma vez, imediatamente após a primeira referencia a class, isto é, no carregamento da memória.
     *  consequentemente será executado antes da chamada ao construtor da classe.
     *  Dentro de um bloco de código estático poderemos acessar somente atributos e métodos estáticos.
     */
    static {
        /*
         * EnumSet.allOf(Class elementType)em Java é usado para criar um conjunto de enum que será usado para conter todos os elementos do elemento especificadoType.
         **/
        for (TipoUsuario tipo : EnumSet.allOf(TipoUsuario.class)) {
            mapString.put(tipo.descricao.toLowerCase(), tipo);
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
    public abstract Boolean precisaDefinirPrazo();
    public abstract Boolean temLimiteEmprestimos();
    public abstract Boolean podePegarRestritos();

}
