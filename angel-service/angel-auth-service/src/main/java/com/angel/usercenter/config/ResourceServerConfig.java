package com.angel.usercenter.config;

import com.angel.usercenter.exception.AuthExceptionEntryPoint;
import com.angel.usercenter.handler.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(ResourceServerConfig.class);

    @Autowired
    private TokenStore tokenStore;


//    @Autowired
//    private CustomAccessDeniedHandler customAccessDeniedHandler;

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/login", "/register","/error/**").permitAll().antMatchers("/**").authenticated()
//                .and().formLogin()
//                .loginPage("/login").loginProcessingUrl("/login").permitAll()
//                .and().csrf().and().httpBasic();
//    }

//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.tokenStore(tokenStore)
//                 .authenticationEntryPoint(new AuthExceptionEntryPoint())//
//                 .accessDeniedHandler(customAccessDeniedHandler);
//    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf();
        // 基于token，所以不需要session  如果基于session 则表使用这段代码
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //对请求进行认证  url认证配置顺序为：1.先配置放行不需要认证的 permitAll() 2.然后配置 需要特定权限的 hasRole() 3.最后配置 anyRequest().authenticated()
                .authorizeRequests()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/login", "/register","/error/**").permitAll()
                .antMatchers("oauth/**").permitAll()
                // 其他请求都需要进行认证,认证通过够才能访问   待考证：如果使用重定向 httpServletRequest.getRequestDispatcher(url).forward(httpServletRequest,httpServletResponse); 重定向跳转的url不会被拦截（即在这里配置了重定向的url需要特定权限认证不起效），但是如果在Controller 方法上配置了方法级的权限则会进行拦截
                .anyRequest().authenticated()
                .and().exceptionHandling()
                // 认证配置当用户请求了一个受保护的资源，但是用户没有通过登录认证，则抛出登录认证异常，MyAuthenticationEntryPointHandler类中commence()就会调用
                .authenticationEntryPoint((req, resp, exception) -> {
                    logger.error(exception.getMessage(), exception);
                    resp.sendRedirect(req.getContextPath() + "/login");
                })
                //用户已经通过了登录认证，在访问一个受保护的资源，但是权限不够，则抛出授权异常，MyAccessDeniedHandler类中handle()就会调用
                .accessDeniedHandler(myAccessDeniedHandler())
                .and()
                //
                .formLogin()
                // 登录url
//                .loginProcessingUrl("/auth/v1/api/login/entry")  // 此登录url 和Controller 无关系
                // .loginProcessingUrl("/auth/v1/api/login/enter")  //使用自己定义的Controller 中的方法 登录会进入Controller 中的方法
                // username参数名称 后台接收前端的参数名
//                .usernameParameter("userAccount")
//                //登录密码参数名称 后台接收前端的参数名
//                .passwordParameter("userPwd")
                //登录成功跳转路径
                .successForwardUrl("/index")
                //登录失败跳转路径
                .failureUrl("/index")
                //登录页面路径
                .loginPage("/login")
                .permitAll()
                //登录成功后 MyAuthenticationSuccessHandler类中onAuthenticationSuccess（）被调用
                .successHandler(myAuthenticationSuccessHandler())
                //登录失败后 MyAuthenticationFailureHandler 类中onAuthenticationFailure（）被调用
                .failureHandler(myAuthenticationFailureHandler())
                .and()
                .logout()
                //退出系统url
                .logoutUrl("/auth/logout")
                //退出系统后的url跳转
                .logoutSuccessUrl("/index")
                //退出系统后的 业务处理
//                .logoutSuccessHandler(myLogoutSuccessHandler())
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                //登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
                // 勾选Remember me登录会在PERSISTENT_LOGINS表中，生成一条记录
                .rememberMe()
                //cookie的有效期（秒为单位
                .tokenValiditySeconds(3600);

//        http.exceptionHandling().authenticationEntryPoint((req, resp, exception) -> {
//            logger.error(exception.getMessage(), exception);
//            resp.sendRedirect(req.getContextPath() + "/login");
//        }).and().authorizeRequests().anyRequest().authenticated();
//        http.formLogin().loginPage("/login").loginProcessingUrl("/login")
//                .successHandler(loginSuccessHandler)
//                .failureHandler(loginFailureHandler).and().csrf().and().httpBasic();
    }



    /**
     * 注册  登录认证 bean
     * @return
     */
    @Bean
    public AuthenticationEntryPoint myAuthenticationEntryPoint(){
        return new JwtAuthenticationEntryPoint();
    }

    /**
     * 注册  认证权限不足处理 bean
     * @return
     */
    @Bean
    public AccessDeniedHandler myAccessDeniedHandler(){
        return new MyAccessDeniedHandler();
    }

    /**
     * 注册  登录成功 处理 bean
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
//        return new MyAuthenticationSuccessHandler();
        return new SavedRequestAwareAuthenticationSuccessHandler() {
			private RequestCache requestCache = new HttpSessionRequestCache();
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
				logger.error("登录成功啦");
				if (authentication != null) {
					if (authentication instanceof OAuth2Authentication) {
						OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
						String access_token = details.getTokenValue();
						logger.error("access_token："+access_token);
						response.setHeader("Authorization", OAuth2AccessToken.BEARER_TYPE + " " + access_token);
                        response.sendRedirect("/index");
					}
					if (authentication instanceof UsernamePasswordAuthenticationToken) {
                        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
                        SecurityContext securityContext = SecurityContextHolder.getContext();
                        securityContext.setAuthentication(authentication);
                        request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
                        response.sendRedirect("/index");
					}

				}
				return;
			}

			@Override
			public void setRequestCache(RequestCache requestCache) {
				this.requestCache = requestCache;
			}

			@Override
			public void setDefaultTargetUrl(String defaultTargetUrl) {
				logger.error("defaultTargetUrl:"+defaultTargetUrl);
				super.setDefaultTargetUrl(defaultTargetUrl);
			}
		};
    }

    /**
     *  注册 登录失败 处理 bean
     * @return
     */
    @Bean
    public AuthenticationFailureHandler myAuthenticationFailureHandler(){
        return new MyAuthenticationFailureHandler();
    }

    /**
     * 注册 退出系统成功 处理bean
     * @return
     */
    @Bean
    public LogoutSuccessHandler myLogoutSuccessHandler(){
        return new MyLogoutSuccessHandler();
    }
}