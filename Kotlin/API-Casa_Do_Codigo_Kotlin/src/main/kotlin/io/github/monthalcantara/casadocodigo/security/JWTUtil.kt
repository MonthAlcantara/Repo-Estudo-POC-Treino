package io.github.monthalcantara.casadocodigo.security

import io.github.monthalcantara.casadocodigo.service.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

//Classe responsável por construir o JWT que será usado na autenticação
@Component
class JWTUtil(private val usuarioService: UsuarioService) {

    @Value("\${jwt.expiration}")
    private lateinit var expiration: String

    //Como esse cara só vai ser carregado em tempo de execução, carregado do properties, eu digo que ele será um lateinit, um inicio tardio
    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(username: String, authorities: MutableCollection<out GrantedAuthority>): String {
        return Jwts
            .builder()
            //A quem pertence esse token
            .setSubject(username)
            //Atribuindo as authorities (roles) a chave role
            .claim("role", authorities)
            // Qual a expiração desse Token (Não é a melhor forma pq temos q gerar na mão)
            .setExpiration(Date(System.currentTimeMillis() + expiration.toLong()))
            // Qual o tipo da assinatura que usaremos no token
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            //Compactar tudo
            .compact()
    }

    //Aqui eu faço o parser do JWT e verifico se ele está de acordo com o o que eu especifiquei
    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    //Preciso verificar também se ele(Token) ainda está válido
    fun getAuthentication(jwt: String?): Authentication {
        val username = Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt).body.subject
        val user = usuarioService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }


}