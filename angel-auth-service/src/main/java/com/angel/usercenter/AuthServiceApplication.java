package com.angel.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**   
 * @ClassName:  AuthServer
 * @Description:OAuth认证服务
 * @author: wanghao
 * @date:   2018年7月19日 下午2:21:34
 * @version V1.0
 * 
 */
@SpringBootApplication
@EnableResourceServer
@EnableEurekaClient
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

}
