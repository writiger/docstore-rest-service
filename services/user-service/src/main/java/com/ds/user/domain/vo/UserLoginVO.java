package com.ds.user.domain.vo;

import lombok.Data;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-17 14:25
 */
@Data
public class UserLoginVO {
    private Long id;
    private String inPasswd;
    private String outPasswd;
}
