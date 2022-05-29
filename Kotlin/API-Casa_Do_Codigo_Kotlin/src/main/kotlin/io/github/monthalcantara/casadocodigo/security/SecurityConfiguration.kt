package io.github.monthalcantara.casadocodigo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfiguration(private val userDetailsService: UserDetailsService, private val jwtUtil: JWTUtil) :
    WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity?) {
        http?.
            /* Necessario deixar HABILITADO para evitar que o token seja interceptado por algum malicioso. Como n éo foco...aqui ele ficará desabilidtado pprecisaria de ainda mais
            config pra funcionar
        */
        csrf()?.disable()?.
            // Autorize as requisições que
        authorizeRequests()?.antMatchers("/autores","/categorias")?.hasAnyAuthority("LEITURA_ESCRITA")?.
            //Como eu estou usando Token, eu preciso gerá-lo primeiro e fornecer ao usuario. Isso será feito no /login
        antMatchers("/login", "/usuarios")?.permitAll()?.
            //independente da requisição (Qualquer requisição)
        anyRequest()?.
            //Que estiver autenticada
        authenticated()

        //Forneço a classe responsável por filtrar a chamada para validar o token
        http?.addFilterBefore(
            JWTLoginFilter(authenticationManager(), jwtUtil),
            UsernamePasswordAuthenticationFilter().javaClass
        )

        //Adicionando novo filtro para validar o token por requisição
        http?.addFilterBefore(
            JWTAuthenticationFilter(jwtUtil),
            UsernamePasswordAuthenticationFilter().javaClass
        )

        //Sobre o gerenciamento de sessão
        http?.sessionManagement()?.
            //Use o modelo Stateless (A aplicação não deve guardar o status da sessão)
        sessionCreationPolicy(SessionCreationPolicy.STATELESS)


    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/h2/**")
    }

    // O cara que vai checar as infos do cara q quer acessar com o banco de dados
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bcryptPasswordEncoder())
    }

    @Bean
    fun bcryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}