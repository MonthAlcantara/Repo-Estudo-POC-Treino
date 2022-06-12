package io.github.monthalcantara.casadocodigo.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Livro(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val titulo: String,
    val resumo: String,
    val sumario: String,
    val preco: BigDecimal,
    val numeroPaginas: Int,
    val isbn: String,
    @ManyToOne val categoria: Categoria,
    @ManyToOne val autor: Autor
)