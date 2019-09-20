package com.angel.auth.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * description:  cookie操作工具类
 * @author ailikes
 * @date  19-9-20 14:03
 * @since 19-9-20
 **/
@SuppressWarnings("unused")
public class CookieUtil {
 

    /**
     * description:  根据Cookie名称得到Cookie对象，不存在该对象则返回Null
     * @author ailikes
     * @date  19-9-20 14:05
     * @since 19-9-20
     * @param request :
     * @param name : cookie名
     * @return javax.servlet.http.Cookie
     **/
    @SuppressWarnings("WeakerAccess")
    public static Cookie getCookie(HttpServletRequest request,String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies==null||cookies.length<1) {
            return null;
        }
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) {
                cookie = c;
                break;
            }
        }
        return cookie;
    }
 
    /**
     * description:  根据Cookie名称直接得到Cookie值
     * @author ailikes
     * @date  19-9-20 14:06
     * @since 19-9-20
     * @param request :
     * @param name : cookie名
     * @return java.lang.String cookie值
     **/
    public static String getCookieValue(HttpServletRequest request,String name) {
        Cookie cookie = getCookie(request,name);
        if(cookie != null){
            return cookie.getValue();
        }
        return null;
    }
 
    /**
     * description:  移除cookie
     * @author ailikes
     * @date  19-9-20 14:07
     * @since 19-9-20
     * @param request :
     * @param response :
     * @param name :
     **/
    public static void removeCookie(HttpServletRequest request,HttpServletResponse response,String name) {
        if (null == name) {
            return;
        }
        Cookie cookie = getCookie(request,name);
        if(null != cookie){
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }
 
    /**
     * description:  添加一条新的Cookie，可以指定过期时间(单位：秒)
     * @author ailikes
     * @date  19-9-20 14:07
     * @since 19-9-20
     * @param response :
     * @param name : cookie名
     * @param value : cookie值
     * @param maxValue : 最大存活周期
     **/
    @SuppressWarnings("WeakerAccess")
    public static void setCookie(HttpServletResponse response,String name,String value, int maxValue) {
        if (StringUtils.isBlank(name)) {
            return;
        }
        if (null == value) {
            value = "";
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxValue);
        response.addCookie(cookie);
    }
 
    /**
     * description:  添加一条新的Cookie，默认30分钟过期时间
     * @author ailikes
     * @date  19-9-20 14:09
     * @since 19-9-20
     * @param name : cookie名
     * @param value : cookie值
     **/
    @SuppressWarnings("unused")
    public static void setCookie(HttpServletResponse response,String name,String value) {
        setCookie(response,name, value, -1);
    }
 
    /**
     * description:  将cookie封装到Map里面
     * @author ailikes
     * @date  19-9-20 14:11
     * @since 19-9-20
     * @param request :
     * @return java.util.Map<java.lang.String,javax.servlet.http.Cookie>
     **/
    @SuppressWarnings("unused")
    public static Map<String,Cookie> getCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length>1){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
 
}