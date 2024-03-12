package com.ds.user.domain.vo;

import com.ds.user.domain.po.User;
import com.ds.user.enums.UserLevel;
import com.ds.user.enums.UserStatus;
import lombok.Data;

/**
 * @author writiger
 * @description 用户登录返回数据
 * @create_at 2024-03-09 19:09
 */
@Data
public class UserLoginVo {
    private String token;
    private String account;
    private String email;
    private String name;
    private UserLevel level;
    private String belong;
    private UserStatus status;
    private String avatar;
    private String uid;

    public UserLoginVo(){

    }

    public UserLoginVo(User user,String token){
        this.token = token;
        this.account = user.getAccount();
        this.email = user.getEmail();
        this.name = user.getName();
        this.level = user.getLevel();
        this.belong = user.getBelong();
        this.status = user.getStatus();
        this.avatar = user.getAvatar();
        this.uid = user.getUuid();
    }
}
