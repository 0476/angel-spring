package com.angel.usercenter.config;

import com.angel.usercenter.handler.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler loginFailureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 基于token，所以不需要session  如果基于session 则表使用这段代码
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                //对请求进行认证  url认证配置顺序为：1.先配置放行不需要认证的 permitAll() 2.然后配置 需要特定权限的 hasRole() 3.最后配置 anyRequest().authenticated()
//                .authorizeRequests()
//                // 对于获取token的rest api要允许匿名访问
//                .antMatchers("/login", "/register","/error/**").permitAll()
//                .antMatchers("oauth/**").permitAll()
//                // 其他请求都需要进行认证,认证通过够才能访问   待考证：如果使用重定向 httpServletRequest.getRequestDispatcher(url).forward(httpServletRequest,httpServletResponse); 重定向跳转的url不会被拦截（即在这里配置了重定向的url需要特定权限认证不起效），但是如果在Controller 方法上配置了方法级的权限则会进行拦截
//                .anyRequest().authenticated()
//                .and().exceptionHandling()
//                // 认证配置当用户请求了一个受保护的资源，但是用户没有通过登录认证，则抛出登录认证异常，MyAuthenticationEntryPointHandler类中commence()就会调用
//                .authenticationEntryPoint((req, resp, exception) -> {
//                        logger.error(exception.getMessage(), exception);
//                        resp.sendRedirect(req.getContextPath() + "/login");
//                })
//                //用户已经通过了登录认证，在访问一个受保护的资源，但是权限不够，则抛出授权异常，MyAccessDeniedHandler类中handle()就会调用
//                .accessDeniedHandler(myAccessDeniedHandler())
//                .and()
//                //
//                .formLogin()
//                // 登录url
////                .loginProcessingUrl("/auth/v1/api/login/entry")  // 此登录url 和Controller 无关系
//                // .loginProcessingUrl("/auth/v1/api/login/enter")  //使用自己定义的Controller 中的方法 登录会进入Controller 中的方法
//                // username参数名称 后台接收前端的参数名
////                .usernameParameter("userAccount")
////                //登录密码参数名称 后台接收前端的参数名
////                .passwordParameter("userPwd")
//                //登录成功跳转路径
//                .successForwardUrl("/index")
//                //登录失败跳转路径
//                .failureUrl("/")
//                //登录页面路径
//                .loginPage("/login")
//                .permitAll()
//                //登录成功后 MyAuthenticationSuccessHandler类中onAuthenticationSuccess（）被调用
//                .successHandler(myAuthenticationSuccessHandler())
//                //登录失败后 MyAuthenticationFailureHandler 类中onAuthenticationFailure（）被调用
//                .failureHandler(myAuthenticationFailureHandler())
//                .and()
//                .logout()
//                //退出系统url
//                .logoutUrl("/auth/v1/api/login/logout")
//                //退出系统后的url跳转
//                .logoutSuccessUrl("/")
//                //退出系统后的 业务处理
//                .logoutSuccessHandler(myLogoutSuccessHandler())
//                .permitAll()
//                .invalidateHttpSession(true)
//                .and()
//                //登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
//                // 勾选Remember me登录会在PERSISTENT_LOGINS表中，生成一条记录
//                .rememberMe()
//                //cookie的有效期（秒为单位
//                .tokenValiditySeconds(3600);
//
////        http.exceptionHandling().authenticationEntryPoint((req, resp, exception) -> {
////            logger.error(exception.getMessage(), exception);
////            resp.sendRedirect(req.getContextPath() + "/login");
////        }).and().authorizeRequests().anyRequest().authenticated();
////        http.formLogin().loginPage("/login").loginProcessingUrl("/login")
////                .successHandler(loginSuccessHandler)
////                .failureHandler(loginFailureHandler).and().csrf().and().httpBasic();
//    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    /**
//     * 注册jwt 认证
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
//        // JwtAuthenticationTokenFilter 过滤器被配置为跳过这个点：/auth/v1/api/login/retrieve/pwd 和 /auth/v1/api/login/entry 不进行token 验证. 通过 SkipPathRequestMatcher 实现 RequestMatcher 接口来实现。
//        List<String> pathsToSkip = Arrays.asList("/auth/v1/api/login/retrieve/pwd","/auth/v1/api/login/entry","/auth/v1/api/login/enter");  //不需要token 验证的url
//        String processingPath = "/auth/v1/api/**"; //　需要验证token　的url
//        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, processingPath);
//        return new JwtAuthenticationTokenFilter(matcher);
//    }

//    /**
//     * 验证登录验证码
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public UsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
//        return new MyUsernamePasswordAuthenticationFilter(authenticationManagerBean(),myAuthenticationSuccessHandler(),myAuthenticationFailureHandler());
//    }
}