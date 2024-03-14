package com.ds.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author writiger
 * @description 整合EMail信息
 * @create_at 2024-03-14 18:09
 */
@Data
public class ToEmail implements Serializable {
    /**
     * 收件人
     */
    private String[] tos;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;

}

