package io.github.monthalcantara.casadocodigo.service

import io.github.monthalcantara.casadocodigo.exception.CampoDuplicadoException
import io.github.monthalcantara.casadocodigo.exception.NaoAutorizadoException
import io.github.monthalcantara.casadocodigo.model.Role
import io.github.monthalcantara.casadocodigo.model.TiposRole
import io.github.monthalcantara.casadocodigo.model.Usuario
import io.github.monthalcantara.casadocodigo.repository.RoleRepository
import io.github.monthalcantara.casadocodigo.repository.UsuarioRepository
import io.github.monthalcantara.casadocodigo.security.UsuarioLogin
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository,
    private val roleRepository: RoleRepository
) : UserDetailsService {

    val log = LoggerFactory.getLogger(UsuarioService::class.java)

    override fun loadUserByUsername(username: String): UserDetails {

        log.info("Buscando usuario na base pelo username {}", username)

        val usuarioEntity = findByUsername(username) ?: throw NaoAutorizadoException()

        log.info("Usuario encontrado com sucesso")

        return UsuarioLogin(usuarioEntity)
    }

    @Transactional
    fun salva(usuario: Usuario): Usuario {
        log.info("Iniciando processo de registro de usuario na base")
        if (findByUsername(usuario.username) != null) {
            log.error("Usuario: {} ja possui registro na base", usuario.username)
            throw CampoDuplicadoException(
                mensagem = "O Username ${usuario.username} ja possui registro no banco",
                campo = "Username"
            )
        }
        //usuario.role = roleRepository.findByNome(TiposRole.LEITURA.name)
        log.info("Usuario: {} registrado na base", usuario.username)
        return usuarioRepository.save(usuario)
    }

    private fun findByUsername(username: String) = usuarioRepository.findByUsername(username)
}