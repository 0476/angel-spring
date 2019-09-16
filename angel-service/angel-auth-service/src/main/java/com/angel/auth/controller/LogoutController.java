package com.angel.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
public class LogoutController {

    @Autowired
    TokenStore tokenStore;

    @RequestMapping("/oauth/logout")
    public void exit(HttpServletRequest request, HttpServletResponse response,String tokenValue) {
        OAuth2Authentication oAuth2Authentication =  tokenStore.readAuthentication(tokenValue);
        OAuth2AccessToken  oAuth2AccessToken = tokenStore.readAccessToken(tokenValue);
        Authentication authentications = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("=============================================");
        System.out.println(oAuth2AccessToken);
        System.out.println(oAuth2AccessToken.getRefreshToken());
        tokenStore.removeAccessToken(oAuth2AccessToken);
        tokenStore.removeRefreshToken(oAuth2AccessToken.getRefreshToken());
        System.out.println(authentications);
        System.out.println("=============================================");
        // token can be revoked here if needed
        new SecurityContextLogoutHandler().logout(request, response, oAuth2Authentication);
        try {
            //sending back to client app
            response.sendRedirect(request.getHeader("referer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/oauth/exit")
    public void exit(HttpServletRequest request, HttpServletResponse response) {
        // token can be revoked here if needed
        new SecurityContextLogoutHandler().logout(request, null, null);
        try {
            //sending back to client app
            response.sendRedirect(request.getHeader("referer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
