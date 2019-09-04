package com.angel.user.service;

import com.angel.common.base.dto.UserLoginDTO;
import com.angel.common.base.entity.User;
import com.angel.common.base.jwt.JWT;
import com.angel.common.base.util.BPwdEncoderUtil;
import com.angel.user.client.AuthServiceClient;
import com.angel.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailService {
  @Autowired
  private UserMapper userMapper;

  @Resource
  private AuthServiceClient authServiceClient;

  public int insertUser(String username, String password) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(BPwdEncoderUtil.encode(password));
    return userMapper.insert(user);
  }

  public UserLoginDTO login(String username, String password) {
//    User user = userMapper.findByUsername(username);
//    if (user == null) {
//      throw new RuntimeException("用户不存在");
//    }
//    if (!BPwdEncoderUtil.matches(password, user.getPassword())) {
//      throw new RuntimeException("用户密码不对");
//    }

    JWT jwt = authServiceClient.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==","password", username, password);
    if (jwt == null) {
      throw new RuntimeException("用户token有问题");
    }
    UserLoginDTO dto = new UserLoginDTO();
    dto.setUser(new User());
    dto.setJwt(jwt);

    return dto;
  }
}