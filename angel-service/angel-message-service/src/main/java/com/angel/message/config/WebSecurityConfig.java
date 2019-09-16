package com.angel.message.config;//自行导入所需依赖包

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)//如果要做本地权限控制，必须加这条注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //必须注入这个bean，配合@EnableGlobalMethodSecurity注解控制用户访问权限
    @Autowired
    AuthenticationManager authenticationManager;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //关闭自身弹窗登录
//        http.httpBasic().disable();
//        http.authorizeRequests().antMatchers("/login","/callback","/logout.do","/oauth/authorize").permitAll();
//        http.authorizeRequests().anyRequest().authenticated();
//        http.headers().frameOptions().disable();//允许WEB的frame框架访问。
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //关闭自身弹窗登录
        http.httpBasic().disable();
        http.headers().frameOptions().disable();//允许WEB的frame框架访问。
        http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login").permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        //先获取到converter列表
//        List<HttpMessageConverter<?>> converters = builder.build().getMessageConverters();
//        for(HttpMessageConverter<?> converter : converters){
//            //因为我们只想要jsonConverter支持对text/html的解析
//            if(converter instanceof MappingJackson2HttpMessageConverter){
//                try{
//                    //先将原先支持的MediaType列表拷出
//                    List<MediaType> mediaTypeList = new ArrayList<>(converter.getSupportedMediaTypes());
//                    //加入对text/html的支持
//                    mediaTypeList.add(MediaType.TEXT_HTML);
//                    //将已经加入了text/html的MediaType支持列表设置为其支持的媒体类型列表
//                    ((MappingJackson2HttpMessageConverter) converter).setSupportedMediaTypes(mediaTypeList);
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
//        return builder.build();
//    }
}