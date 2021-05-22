package io.github.monthalcantara.aws_project01.model;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 32)
    private String nome;

    @Column(nullable = false, length = 24, unique = true)
    private String code;

    @Column(nullable = false, length = 8)
    private float price;

    public Product() {
    }

    public Product(Long id, String nome, String code, float price) {
        this.id = id;
        this.nome = nome;
        this.code = code;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
