package com.angel.auth.service;

import com.angel.auth.entity.Authority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class JdbcUserDetailsServiceImpl implements UserDetailsService{


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//
//        if(credentials==null){
//
//            throw new UsernameNotFoundException("User"+username+"can not be found");
//        }

        User user = new User("oauth_admin","$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2",true,true,true,true,getAuthorities());

        return  user;
    }

    public List<Authority> getAuthorities(){
        Authority a = new Authority();
        a.setAuthority("ROLE_OAUTH_ADMIN");
        List<Authority> list = new ArrayList();
        list.add(a);
        return list;
    }
}
