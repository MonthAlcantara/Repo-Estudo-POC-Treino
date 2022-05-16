package io.github.monthalcantara.forum.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Usuario(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,
    val nome: String,
    val email: String
)