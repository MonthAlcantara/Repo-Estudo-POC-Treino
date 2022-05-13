package io.github.monthalcantara.producer.model;

public class Mensagem {

    private String nome;

    private String conteudo;

    public Mensagem(String nome, String conteudo) {
        this.nome = nome;
        this.conteudo = conteudo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "nome='" + nome + '\'' +
                ", conteudo='" + conteudo + '\'' +
                '}';
    }
}
