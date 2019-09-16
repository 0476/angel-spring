package com.angel.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;

@RestController
public class DemoController {
    @RequestMapping("/hi")
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
        LinkedHashMap oAuth2AuthenticationDetails = (LinkedHashMap)oAuth2Authentication.getUserAuthentication().getDetails();
        System.out.println("=============================================");
        System.out.println(oAuth2Authentication);
        System.out.println(principal);
        System.out.println(authentication);
        System.out.println("=============================================");
        return oAuth2Authentication;
    }
}