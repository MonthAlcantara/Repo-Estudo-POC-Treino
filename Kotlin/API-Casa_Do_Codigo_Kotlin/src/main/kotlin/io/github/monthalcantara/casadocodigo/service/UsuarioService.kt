package io.github.monthalcantara.casadocodigo.service

import io.github.monthalcantara.casadocodigo.exception.CampoDuplicadoException
import io.github.monthalcantara.casadocodigo.exception.NaoAutorizadoException
import io.github.monthalcantara.casadocodigo.model.TiposRole
import io.github.monthalcantara.casadocodigo.model.Usuario
import io.github.monthalcantara.casadocodigo.repository.RoleRepository
import io.github.monthalcantara.casadocodigo.repository.UsuarioRepository
import io.github.monthalcantara.casadocodigo.security.UsuarioLogin
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository,
    private val roleRepository: RoleRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val usuarioEntity = findByUsername(username) ?: throw NaoAutorizadoException()
        return UsuarioLogin(usuarioEntity)
    }

    @Transactional
    fun salva(usuario: Usuario): Usuario {
        if (findByUsername(usuario.username) != null)
            throw CampoDuplicadoException(
                mensagem = "O Username ${usuario.username} ja possui registro no banco",
                campo = "Username"
            )
        val role = roleRepository.findByNome(TiposRole.LEITURA.name)
        usuario.role = mutableListOf(role!!)
        return usuarioRepository.save(usuario)
    }

    private fun findByUsername(username: String) = usuarioRepository.findByUsername(username)
}