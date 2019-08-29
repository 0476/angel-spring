package com.angel.common.base.interceptor;

import com.angel.common.utils.IPUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @descriptioon:IP拦截器，用于验证IP白名单
 * @author ailikes
 * @since 2019-08-29
 * @version 1.0.0
 */
public class IPInterceptor implements HandlerInterceptor {

    private static final Logger logger= LoggerFactory.getLogger(IPInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //过滤ip,若用户在白名单内，则放行
        String ipAddress= IPUtils.getRealIP(request);
        logger.info("USER IP ADDRESS IS =>"+ipAddress);
        if(StringUtils.isBlank(ipAddress)){
            response.getWriter().append("<h1 style=\"text-align:center;\">The system did not get your IP address!</h1>");
            return false;
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}