package com.ds.user.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ds.user.enums.UserLevel;
import com.ds.user.enums.UserStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author writiger
 * @description 用户持久层类
 * @create_at 2024-03-09 18:11
 */
@Data
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户名
     */
    private String name;

    /**
     * 类型(0-超级管理员,1-地方管理员,2-普通用户)
     */
    private UserLevel level;

    /**
     * 密码
     */
    private String password;

    /**
     * 所属单位
     */
    private String belong;

    /**
     * 用户状态(0-封禁,1-正常)
     */
    private UserStatus status;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * uuid 22位
     */
    private String uuid;


}
