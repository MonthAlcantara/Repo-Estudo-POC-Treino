package io.github.monthalcantara.casadocodigo.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name ="autores")
data class Autor(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(nullable = false) val email: String,
    @Column(nullable = false) val nome: String,
    @Column(nullable = false) val descricao: String,
    val instanteCriacao: LocalDateTime
)

