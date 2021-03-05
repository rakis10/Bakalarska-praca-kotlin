package com.example.kotlinbp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices
//import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import kotlin.jvm.Throws
@Configuration
@EnableResourceServer
class ResourceServerConfig(
//        val tokenServices : ResourceServerTokenServices
        ) : ResourceServerConfigurerAdapter() {

    @Autowired
    private val tokenStore: TokenStore? = null

    @Throws(Exception::class)
    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.tokenStore(tokenStore)

    }

    override fun configure(http: HttpSecurity?) {
//        http?.csrf()?.disable()
        http?.authorizeRequests()?.anyRequest()?.permitAll()
//        .csrf()?.disable()?
    }
}