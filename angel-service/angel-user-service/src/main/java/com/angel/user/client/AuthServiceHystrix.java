package com.angel.user.client;

import com.angel.common.base.jwt.JWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceHystrix implements AuthServiceClient {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public JWT getToken( String client_id
          ,String client_secret
          ,String grantType
          ,String scope
          ,String username
          ,String password) {
    logger.error("获取JWT Token失败, client_id: {}, grantType: {}, username: {}, password: {}",client_id, grantType, username, password);
    return null;
  }

}