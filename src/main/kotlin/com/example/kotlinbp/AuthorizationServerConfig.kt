package com.example.kotlinbp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig(): AuthorizationServerConfigurerAdapter()
{

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Bean
    fun tokenStore(): TokenStore{
        val tokenStore  =  JwtTokenStore(jwtAccessTokenConverter())
        return tokenStore
    }
    @Bean
    fun jwtAccessTokenConverter() : JwtAccessTokenConverter{
        val jwtAccessTokenConverter : JwtAccessTokenConverter = JwtAccessTokenConverter()
        jwtAccessTokenConverter.setSigningKey("1234")
        return  jwtAccessTokenConverter
    }


   override fun configure(clients: ClientDetailsServiceConfigurer?) {
//
       clients!!.inMemory()
               .withClient("clientId")
               .secret("abcd")
               .authorizedGrantTypes("password", "refresh_token", "authorization_token")
               .scopes("write") //upravitelne neskor

   }
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
//

        // toto je hlavny point
        //upravujeme endpointy ,
        endpoints!!.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter(jwtAccessTokenConverter())
    }
}