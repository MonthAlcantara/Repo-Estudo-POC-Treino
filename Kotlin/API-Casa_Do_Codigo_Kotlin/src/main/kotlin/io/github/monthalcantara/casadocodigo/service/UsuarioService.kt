package io.github.monthalcantara.casadocodigo.service

import io.github.monthalcantara.casadocodigo.model.Usuario
import io.github.monthalcantara.casadocodigo.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val usuarioEntity = usuarioRepository.findByUsername(username) ?: throw RuntimeException()
        return UsuarioLogin(usuarioEntity)
    }

    @Transactional
    fun salva(usuario: Usuario): Usuario {
        return usuarioRepository.save(usuario)
    }
}