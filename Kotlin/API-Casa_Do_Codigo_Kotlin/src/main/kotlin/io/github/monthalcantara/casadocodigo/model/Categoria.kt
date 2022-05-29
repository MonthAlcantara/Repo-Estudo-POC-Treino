package io.github.monthalcantara.casadocodigo.model

import javax.persistence.*

@Entity
@Table(name = "categorias")
data class Categoria(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(nullable = false, unique = true) val nome: String
)