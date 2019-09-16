//package com.angel.auth.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter{
//
//
//    @Autowired
//    UserDetailsService userDetailsServiceBean;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http.csrf().disable();
//    }
//}