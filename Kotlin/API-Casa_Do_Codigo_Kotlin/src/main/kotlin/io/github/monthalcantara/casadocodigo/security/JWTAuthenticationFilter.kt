package io.github.monthalcantara.casadocodigo.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

//Essa classe vai ser responsável por filtrar as requisições
class JWTAuthenticationFilter(private val jwtUtil: JWTUtil) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse,filterChain: FilterChain) {
//        pegando o valor da chave "Authorization" que veio na request
        val token = request.getHeader("Authorization")
        val jwt = getTokenDetail(token)

        if (jwtUtil.isValid(jwt)) {
            val authentication = jwtUtil.getAuthentication(jwt)
//            Com o usuario válido, eu o seto no contexto da aplicação
            SecurityContextHolder.getContext().authentication = authentication

        }
        filterChain.doFilter(request, response)
    }

    private fun getTokenDetail(token: String?): String? {
        //Aqui eu preciso retirar o Bearer e o espaço que vem antes do token de fato
        return token?.let {// O let é tipo o apply mas se o token for null ele devolve null
            it.startsWith("Bearer ")
            it.substring(7, it.length)
        }
    }

}
