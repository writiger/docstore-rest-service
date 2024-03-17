package com.ds.common.domain.vo;

import com.ds.common.enums.UserLevel;
import com.ds.common.enums.UserStatus;
import lombok.Data;

/**
 * @author writiger
 * @description 用户登录返回数据
 * @create_at 2024-03-09 19:09
 */
@Data
public class UserVo {
    private String token;
    private String account;
    private String email;
    private String name;
    private UserLevel level;
    private String belong;
    private UserStatus status;
    private String avatar;
    private String uid;
}
