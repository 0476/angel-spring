package com.angel.user.client;

import com.angel.common.base.jwt.JWT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "auth-service", fallback = AuthServiceHystrix.class)
public interface AuthServiceClient {
  @PostMapping(value = "/oauth/token")
  JWT getToken(@RequestParam(value = "client_id",defaultValue = "user-service") String client_id
          ,@RequestParam(value = "client_secret",defaultValue = "123456") String client_secret
          ,@RequestParam(value = "grant_type",defaultValue = "password") String grantType
          ,@RequestParam(value = "scope",defaultValue = "service") String scope
          ,@RequestParam("username") String username
          ,@RequestParam("password") String password);
}