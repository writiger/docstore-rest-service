package com.ds.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ds.common.exception.BadRequestException;
import lombok.Getter;

@Getter
public enum UserStatus {
    FROZEN(0,"封禁中"),
    NORMAL(1,"正常"),
    ;
    @EnumValue
    int value;
    String desc;

    UserStatus(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public static UserStatus of(int value){
        if  (value == 0){
            return FROZEN;
        }
        if (value == 1){
            return NORMAL;
        }
        throw new BadRequestException("账号状态错误");
    }
}
