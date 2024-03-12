package com.ds.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ds.common.exception.BadRequestException;
import lombok.Getter;

@Getter
public enum UserLevel {
    ROOT(0,"超级管理员"),
    ADMIN(1,"区域管理员"),
    NORMAL(2,"普通用户"),
    ;
    @EnumValue
    int value;
    String desc;

    UserLevel(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public static UserLevel of (int value){
        switch (value){
            case 0:{
                return ROOT;
            }
            case 1:{
                return ADMIN;
            }
            case 2:{
                return NORMAL;
            }
            default:{
                throw new BadRequestException("用户等级错误");
            }
        }
    }
}
