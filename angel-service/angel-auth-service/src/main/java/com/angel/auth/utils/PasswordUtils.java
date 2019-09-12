package com.angel.auth.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * description:  密码加密工具类
 * @author ailikes
 * @date  2019-09-12 11:25
 * @since 2019-09-12
 **/
public class PasswordUtils {
    /**
     * description:  密码加密
     * @author ailikes
     * @date  2019-09-12 11:24
     * @since 2019-09-12
     * @param password :
     * @return java.lang.String
     **/
    public static String passwordEncoder(String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        return encodedPassword;
    }
}
