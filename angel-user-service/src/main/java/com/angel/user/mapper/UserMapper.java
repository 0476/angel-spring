package com.angel.user.mapper;

import com.angel.common.base.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User> {

  User findByUsername(String username);

}