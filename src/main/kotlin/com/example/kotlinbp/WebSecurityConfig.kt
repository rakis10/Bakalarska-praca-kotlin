package com.example.kotlinbp

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class WebSecurityConfig(): WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return NoOpPasswordEncoder.getInstance()
    }
    @Bean
    override fun   userDetailsService() :UserDetailsService{
        val manager =  InMemoryUserDetailsManager()
        var user = User.withUsername("foo")
                .password("foo")
                .authorities("USER")
                .build()
        manager.createUser(user)
        return manager
    }
//    @Bean
////    @Primary
//    fun tokenServices(): DefaultTokenServices {
//        val defaultTokenServices = DefaultTokenServices()
//        defaultTokenServices.setTokenStore(JwtTokenStore(getAccessTokenConverter()))
//        defaultTokenServices.setSupportRefreshToken(true)
//        return defaultTokenServices
//    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager{
        return super.authenticationManagerBean()
    }
    override fun configure(http: HttpSecurity?) {
//        http?.csrf()?.disable()
        http?.csrf()?.disable()?.authorizeRequests()?.anyRequest()?.permitAll()
//        http?.cors()
//        http?.authorizeRequests().
    }

}