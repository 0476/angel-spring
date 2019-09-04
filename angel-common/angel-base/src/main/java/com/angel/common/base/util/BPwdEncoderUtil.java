package com.angel.common.base.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * @Description:  密码验证工具类
 * @Author ailikes
 * @Date  2019-09-04 16:10
 * @Since 2019-09-04
 **/
public class BPwdEncoderUtil {

  private static final PasswordEncoder ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

  public static String encode(String password) {
    return ENCODER.encode(password);
  }

  public static boolean matches(CharSequence rawPassword, String encodedPassword) {
    return ENCODER.matches(rawPassword, encodedPassword);
  }

}