package io.github.monthalcantara.forum.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topico(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @ManyToOne val curso: Curso,
    @ManyToOne val autor: Usuario,
    @Enumerated(EnumType.STRING) val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    @OneToMany(mappedBy = "topico") val respostas: List<Resposta> = ArrayList()
)