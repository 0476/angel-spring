package com.angel.common.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
/**
 * @Description:  用户信息
 * @Author ailikes
 * @Date  2019-09-04 15:56
 * @Since 2019-09-04
 **/
@TableName("sys_user")
public class User implements UserDetails, Serializable {

  private static final long serialVersionUID = 4047958504168236627L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private String username;


  private String password;

  private List<Role> authorities;

  private Instant lastPasswordResetDate;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setAuthorities(List<Role> authorities) {
    this.authorities = authorities;
  }

  public Instant getLastPasswordResetDate() {
    return lastPasswordResetDate;
  }

  public void setLastPasswordResetDate(Instant lastPasswordResetDate) {
    this.lastPasswordResetDate = lastPasswordResetDate;
  }
}