package com.ds.user.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-14 17:10
 */
@Component
public class VerifyTool {
    public static String getVCode(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
