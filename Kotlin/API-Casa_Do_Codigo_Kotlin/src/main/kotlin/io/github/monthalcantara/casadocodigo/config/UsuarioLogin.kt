package io.github.monthalcantara.casadocodigo.config

import io.github.monthalcantara.casadocodigo.model.UsuarioEntity
import org.springframework.security.core.userdetails.UserDetails

// Esse cara é que serpa usado para comparar com o cidadão salvo no BD
data class UsuarioLogin(val usuarioEntity: UsuarioEntity?) : UserDetails {
    override fun getAuthorities() = null

    override fun getPassword() = usuarioEntity?.password

    override fun getUsername() = usuarioEntity?.username

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

}
