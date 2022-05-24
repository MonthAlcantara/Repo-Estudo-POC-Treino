package io.github.monthalcantara.casadocodigo.config

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
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
@Configuration
class SecurityConfiguration(private val userDetailsService: UserDetailsService) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http?.
            // Autorize as requisições que
        authorizeRequests()?.//antMatchers(HttpMethod.POST, "/usuarios")?.permitAll()?.
            //independente da requisição (Qualquer requisição)
        anyRequest()?.
            //Que estiver autenticada
        authenticated()?.
            //e
        and()?.
            //Sobre o gerenciamento de sessão
        sessionManagement()?.
            //Use o modelo Stateless (A aplicação não deve guardar o status da sessão)
        sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.and()?.
            //Sobre o formulario padrão do Spring
        formLogin()?.
            // Desabilite
        disable()?.
            //A forma de autenticação será o http basic
        httpBasic()

    }

//    override fun configure(web: WebSecurity) {
//        web.ignoring().antMatchers(
//            "/h2/**",
//            "/usuarios/**",
//            "/configuration/**"
//        )
//    }

    // O cara que vai checar as infos do cara q quer acessar com o banco de dados
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bcryptPasswordEncoder())
    }

    @Bean
    fun bcryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}