package com.angel.usercenter.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author: ailikes
 * @Description:
 * @Date: Created in 11:36 2018/10/11
 * @Modified By:
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,AuthenticationException authException)throws ServletException {
        try {
            response.sendRedirect("/error/401");
        } catch (Exception e) {
            throw new ServletException();
        }
    }

}