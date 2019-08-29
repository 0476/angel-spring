package com.angel.common.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:实体基类
 * @author ailikes
 * @since 2019-08-29
 * @version 1.0.0
 */
public abstract class BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(type=IdType.AUTO)
    private Long id;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField("update_user")
    private Long updateUser;

    /**
     * 是否删除
     */
    @TableField("is_deleted")
    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;

    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract Date getCreateTime();

    public abstract void setCreateTime(Date createTime);

    public abstract Long getCreateUser();

    public abstract void setCreateUser(Long createUser);

    public abstract Date getUpdateTime();

    public abstract void setUpdateTime(Date updateTime);

    public abstract Long getUpdateUser();

    public abstract void setUpdateUser(Long updateUser);

    public abstract Integer getIsDeleted();

    public abstract void setIsDeleted(Integer isDeleted);
}
