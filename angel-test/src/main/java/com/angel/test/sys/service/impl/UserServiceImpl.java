package com.angel.test.sys.service.impl;

import com.angel.test.sys.entity.User;
import com.angel.test.sys.mapper.UserMapper;
import com.angel.test.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ailikes
 * @since 2019-08-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
