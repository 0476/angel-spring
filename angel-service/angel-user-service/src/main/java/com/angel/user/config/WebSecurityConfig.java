package com.angel.user.config;//自行导入所需依赖包

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)//如果要做本地权限控制，必须加这条注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //必须注入这个bean，配合@EnableGlobalMethodSecurity注解控制用户访问权限
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        //关闭自身弹窗登录
        http.headers().frameOptions().disable();//允许WEB的frame框架访问。
        http.httpBasic().disable();
        http.csrf().disable();
        http.headers().frameOptions().disable();//允许WEB的frame框架访问。
        http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login","/logout").permitAll().anyRequest()
                .authenticated();
        http.logout().logoutUrl("/logout").logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
//                .logoutSuccessHandler(logoutSuccessHandler)
                .logoutSuccessUrl("http://localhost:2000/auth/oauth/exit")
                .deleteCookies("USERSESSIONID")
                .permitAll();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}