package com.angel.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
public class CoreController<T> {

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(path = "/user",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> user(@RequestHeader String authorization) {
        //必须通过客户端{携带的token在服务端的token存储中获取用户信息。
        //header中 Authorization传过来的格式为[type token]的格式
        //因此必须先对Authorization传过来的数据进行分隔authorization.split(" ")[1]才是真正的token
        Map<String, Object> map = new HashMap<>();
        OAuth2Authentication authen = null;
        try {
            authen = tokenStore.readAuthentication(authorization.split(" ")[1]);
            if (authen == null) {
                map.put("error", "invalid token !");
                return map;
            }
        } catch (Exception e) {
            System.out.println(e);
            map.put("error", e);
            return map;
        }
        //注意这两个key都不能随便填，都是和客户端进行数据处理时进行对应的。
        map.put("user", authen.getPrincipal());
        map.put("authorities", authen.getAuthorities());
        return map;

    }

    @RequestMapping("/hi")
    @ResponseBody
    public String hi() {
        return "hi, 你好";
    }

    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String hello() {
        return "hello, 你好";
    }

    @RequestMapping("/getPrincipal")
    public OAuth2Authentication getPrincipal(OAuth2Authentication oAuth2Authentication,
                                             Principal principal,
                                             Authentication authentication) {
        System.out.println("=============================================");
        System.out.println(oAuth2Authentication);
        System.out.println(principal);
        System.out.println(authentication);
        System.out.println("=============================================");
        return oAuth2Authentication;
    }

}