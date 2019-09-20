package com.angel.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * description:  自定义MVC配置
 *
 * @author ailikes
 * @date 19-9-20 17:14
 * @since 19-9-20
 **/
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    /**
     * description:  本地化设置，默认为中文国际化
     * @author ailikes
     * @date  19-9-20 18:30
     * @since 19-9-20

     * @return org.springframework.web.servlet.LocaleResolver
     **/
    @Bean(name="localeResolver")
    public LocaleResolver localeResolverBean() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        sessionLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return sessionLocaleResolver;
    }
    /**
     * description:  本地化拦截器
     * @author ailikes
     * @date  19-9-20 18:30
     * @since 19-9-20
     * @return org.springframework.web.servlet.i18n.LocaleChangeInterceptor
     **/
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /**
     * description:  增加拦截器
     * @author ailikes
     * @date  19-9-20 18:30
     * @since 19-9-20
     * @param registry :
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

}