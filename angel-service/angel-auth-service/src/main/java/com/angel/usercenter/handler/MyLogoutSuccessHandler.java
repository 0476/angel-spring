package com.angel.usercenter.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 *
 * @FileName: MyLogoutSuccessHandler
 * @author    ailikes
 * @version   1.0.0
 * @description:   用户退出系统成功后 需要做的业务操作
 * @explain   当用户退出系统成功后则会进入到此类并执行相关业务
 *
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(MyLogoutSuccessHandler.class);
//    @Autowired
//    private RedisUtil redisUtil;
//   @Autowired
//    private UserUtils userUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.error("退出登录");
//        //根据token清空redis
//        String userKey =  RedisKeys.USER_KEY;
//        String token = userUtils.getUserToken(httpServletRequest);
//        redisUtil.hdel(userKey,token);
//        SecurityContextHolder.clearContext();  //清空上下文
//        httpServletRequest.getSession().removeAttribute("SPRING_SECURITY_CONTEXT"); // 从session中移除
//        //退出信息插入日志记录表中
//        ResultUtil.writeJavaScript(httpServletResponse,ErrorCodeEnum.SUCCESS,"退出系统成功.");
    }
}