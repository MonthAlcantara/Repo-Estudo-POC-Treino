package io.github.monthalcantara.casadocodigo.security

import io.github.monthalcantara.casadocodigo.model.Usuario
import org.springframework.security.core.userdetails.UserDetails

// Esse cara é que serpa usado para comparar com o cidadão salvo no BD
data class UsuarioLogin(private val usuario: Usuario) : UserDetails {
    override fun getAuthorities() = usuario.role

    override fun getPassword() = usuario.password

    override fun getUsername() = usuario.username

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

}
