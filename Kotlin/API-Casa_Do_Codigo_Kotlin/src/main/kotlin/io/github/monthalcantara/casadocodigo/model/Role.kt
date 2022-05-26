package io.github.monthalcantara.casadocodigo.model

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
@Table(name = "role")
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,
    private val nome: String

) : GrantedAuthority {
    override fun getAuthority() = nome

}