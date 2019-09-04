package com.angel.common.base.dto;

import com.angel.common.base.entity.User;
import com.angel.common.base.jwt.JWT;
/**
 * @Description:  用户登录DTO
 * @Author ailikes
 * @Date  2019-09-04 15:58
 * @Since 2019-09-04
 **/
public class UserLoginDTO {

  private User user;

  private JWT jwt;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public JWT getJwt() {
    return jwt;
  }

  public void setJwt(JWT jwt) {
    this.jwt = jwt;
  }
}