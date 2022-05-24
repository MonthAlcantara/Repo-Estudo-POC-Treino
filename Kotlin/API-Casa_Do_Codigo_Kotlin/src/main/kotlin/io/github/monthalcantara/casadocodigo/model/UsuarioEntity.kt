package io.github.monthalcantara.casadocodigo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "usuarios")
data class UsuarioEntity(
    @Id @GeneratedValue
    var id: Long? = null,
    val username: String,
    val password: String
)