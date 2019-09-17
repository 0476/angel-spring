package com.angel.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * description:
 *
 * @author ailikes
 * @version 1.0.0
 * @date 19-9-17 11:36
 * @since 19-9-17 11:36
 **/
@Configuration
@EnableResourceServer
@Order(6)
public class ResourcesConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //关闭自身弹窗登录
        http.httpBasic().disable();
        http.headers().frameOptions().disable();//允许WEB的frame框架访问。
        http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login").permitAll()
                .anyRequest()
                .authenticated();
    }

//    @Autowired
//    private OAuth2ClientProperties oAuth2ClientProperties;
//
//    @Autowired
//    private AuthorizationServerProperties authorizationServerProperties;
//
//    @Bean
//    public ResourceServerTokenServices tokenServices() {
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        remoteTokenServices.setCheckTokenEndpointUrl(authorizationServerProperties.getCheckTokenAccess());
//        remoteTokenServices.setClientId(oAuth2ClientProperties.getClientId());
//        remoteTokenServices.setClientSecret(oAuth2ClientProperties.getClientSecret());
//        remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
//        return remoteTokenServices;
//    }
//
//    @Bean
//    public AccessTokenConverter accessTokenConverter() {
//        return new DefaultAccessTokenConverter();
//    }
//
//    @Bean
//    @ConfigurationProperties("security.oauth2.authorization")
//    public AuthorizationServerProperties authorizationServerProperties(){
//        return new AuthorizationServerProperties();
//    }
}
