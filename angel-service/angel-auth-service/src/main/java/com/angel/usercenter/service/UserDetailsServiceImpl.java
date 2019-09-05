package com.angel.usercenter.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description:
 * @Author ailikes
 * @Since 2019-09-03 14:13
 * @Date 2019-09-03 14:13
 * @Version 1.0.0
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        try {
            com.angel.common.base.entity.User favUser = new com.angel.common.base.entity.User();
            favUser.setUsername("user");
            favUser.setPassword("{bcrypt}$2a$10$izhHfCloYRkH45wNh0x3Cus94JVgejlTh/l4Ii3jS8Q4.BNtZQCcW");
            Collection<GrantedAuthority> authList = getAuthorities();
            userDetails = new User(username, favUser.getPassword(),true,true,true,true,authList);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return userDetails;
    }

    /**  * 获取用户的角色权限,为了降低实验的难度，这里去掉了根据用户名获取角色的步骤     * @param    * @return   */
    private Collection<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority("USER"));
        authList.add(new SimpleGrantedAuthority("ADMIN"));
        return authList;
    }

}
