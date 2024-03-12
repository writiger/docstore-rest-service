package com.ds.user.utils;

import java.util.Random;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-12 16:18
 */
public class VerifyTool {
    public static String generateVerifyCode(){

            Random rm = new Random();

            // 获得随机数
            double pross = (1 + rm.nextDouble()) * Math.pow(10, 6);

            // 将获得的获得随机数转化为字符串
            String fixLenthString = String.valueOf(pross);

            // 返回固定的长度的随机数
            return fixLenthString.substring(1, 7);
    }
}
