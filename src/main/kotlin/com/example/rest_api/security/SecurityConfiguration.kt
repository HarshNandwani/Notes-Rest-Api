package com.example.rest_api.security

import com.example.rest_api.service.user.NotesUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var notesUserDetailsService: NotesUserDetailsService

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(notesUserDetailsService)
    }

    override fun configure(http: HttpSecurity) {
        val filter = AuthenticationFilter(authenticationManagerBean())
        filter.setFilterProcessesUrl("/auth/login")

        http
            .csrf().disable()
            .authorizeRequests().antMatchers("/auth/**").permitAll()
        http.authorizeRequests().antMatchers("/api/**").fullyAuthenticated()

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.addFilter(filter)
        http.addFilterBefore(AuthorizationFilter(), AuthenticationFilter::class.java)
    }


    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }

}
