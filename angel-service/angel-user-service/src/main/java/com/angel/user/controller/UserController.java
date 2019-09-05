package com.angel.user.controller;

import com.angel.common.base.dto.UserLoginDTO;
import com.angel.user.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserDetailService userDetailService;

  @PostMapping("/register")
  public int postUser(@RequestParam("username") String username,
                       @RequestParam("password") String password) {
    return userDetailService.insertUser(username, password);
  }

  @PostMapping("/login")
  public UserLoginDTO login(@RequestParam("username") String username,@RequestParam("password") String password) {
    return userDetailService.login(username, password);
  }
}