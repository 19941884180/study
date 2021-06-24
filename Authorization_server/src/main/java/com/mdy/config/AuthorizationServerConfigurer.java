package com.mdy.config;

import com.mdy.service.impl.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    private static final String RESOURCE_IDS = "order";

    private static final String CLIENT_ID1 = "client_id1";

    private static final String CLIENT_ID2 = "client_id2";


    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String fianlSecret = new BCryptPasswordEncoder().encode("123456");
        clients.inMemory().withClient(CLIENT_ID1).resourceIds(RESOURCE_IDS)
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("all").secret(fianlSecret)
                .and()
                .withClient(CLIENT_ID2).resourceIds(RESOURCE_IDS)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("all").secret(fianlSecret);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.userDetailsService(userDetailService)

                .tokenStore(new RedisTokenStore(connectionFactory))
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }
}
