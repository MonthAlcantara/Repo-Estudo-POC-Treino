package io.github.monthalcantara.casadocodigo.security

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.monthalcantara.casadocodigo.config.JWTUtil
import io.github.monthalcantara.casadocodigo.model.Credentials
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/*
Essa classe vai ajudar na autenticação do usuario e na geração do token
1 - Pego a requisição do usuario no body
2 - converto para um objeto meu (Credentials) usando o ObjectMapper
3 - passo o username e password vinda no body para autenticação no UsernamePasswordAuthenticationToken
4 - O resultado disso é passado para o authManager verificar no banco seo cara realmente existe
5 - Estando tudo ok, é gerado o token e enviado de volta para o header no response ao usuario
 */

class JWTLoginFilter(
    private val authManager: AuthenticationManager,
    private val jwtUtil: JWTUtil
) : UsernamePasswordAuthenticationFilter() {
    // Esse método vai pegar a request do usuario e extrair o Body dela ( O usuario vai informar as credenciais dele no body) e vamos autenticar o usuario se os dados estiverm ok
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        // Object mapper facilita a conversão do json em uma classe do meu projeto
        val (username, password) = ObjectMapper().readValue(request?.inputStream, Credentials::class.java)
        //Através desse objeto meu usuario poderá ser autenticado. O UsernamePasswordAuthenticationToken faz isso pra gente
        val token = UsernamePasswordAuthenticationToken(username, password)
        //Esse cara faz, por debaixo dos panos, o mesmo processo da implementação do basic authenticate que eu fiz na mão
        return authManager.authenticate(token)
    }

    //Uma vez o usuario sendo autenticado, esse método gera um token para o usuario
    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        //authResult?.principal as UserDetails <- Cast em Kotlin
        val user = (authResult?.principal as UserDetails)
        val token = jwtUtil.generateToken(user.username, user.authorities)
        // Adiciono no header da resposta o token gerado pelo JWTUtil
        response?.addHeader("Authorization", "Bearer $token")
    }
}
