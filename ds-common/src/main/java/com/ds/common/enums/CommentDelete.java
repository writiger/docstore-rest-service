package com.ds.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ds.common.exception.BadRequestException;
import lombok.Getter;

/**
 * @author writiger
 * @description
 * @create_at 2024-05-22 10:28
 */
@Getter
public enum CommentDelete {
    EXIST(0,"未删除"),
    DELETED(1,"已删除"),
    ;
    @EnumValue
    int value;
    String desc;

    CommentDelete(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public static CommentDelete of(int value){
        if  (value == 0){
            return EXIST;
        }
        if (value == 1){
            return DELETED;
        }
        throw new BadRequestException("账号状态错误");
    }
}
