package com.ds.user.utils;

import java.util.Random;

/**
 * @author writiger
 * @description 生成6位验证码
 * @create_at 2024-03-14 18:05
 */
public class VerifyTool {
    /**
     *
     * @return 6位数验证码
     */
    public static String generateCode(){
            StringBuilder str = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                str.append(random.nextInt(10));
            }
            return str.toString();
    }
}
