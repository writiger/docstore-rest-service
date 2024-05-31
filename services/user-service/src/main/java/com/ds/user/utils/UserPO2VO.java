package com.ds.user.utils;

import com.ds.user.domain.po.User;
import com.ds.common.domain.vo.UserVo;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-17 12:42
 */
public class UserPO2VO {
    public static UserVo PO2VO(User user){
        UserVo userVo = new UserVo();
        userVo.setToken(user.getId().toString());
        userVo.setAccount(user.getAccount());
        userVo.setEmail(user.getEmail());
        userVo.setName(user.getName());
        userVo.setLevel( user.getLevel());
        userVo.setBelong(user.getBelong());
        userVo.setStatus(user.getStatus());
        userVo.setAvatar(user.getAvatar());
        userVo.setUuid(user.getUuid());
        return userVo;
    }
}
