package io.github.monthalcantara.casadocodigo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "usuario")
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val username: String,
    val password: String,
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_role")
    var role: List<Role> = mutableListOf()
)
