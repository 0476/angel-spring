package com.angel.common.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.security.core.GrantedAuthority;
/**
 * @Description:  角色信息
 * @Author ailikes
 * @Date  2019-09-04 15:57
 * @Since 2019-09-04
 **/
public class Role implements GrantedAuthority {

  private static final long serialVersionUID = 8386864187793300772L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private String name;

  @Override
  public String getAuthority() {
    return name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}