package com.angel.message.config;

import com.angel.common.base.interceptor.AilikesWebInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 功能描述:通用拦截器配置
 *
 * @author 徐大伟
 * @version 1.0.0
 * @date 19-6-11 17:34
 */
@Configuration
public class AilikesInterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //通用拦截器
        registry.addInterceptor(ailikesWebInterceptor()).addPathPatterns("/**").excludePathPatterns("/upload/**","/static/**","/webjars/**","/resources/**");
    }

    @Bean
    public AilikesWebInterceptor ailikesWebInterceptor(){
        return new AilikesWebInterceptor();
    }

}
