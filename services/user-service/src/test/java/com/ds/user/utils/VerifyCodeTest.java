package com.ds.user.utils;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-12 16:24
 */
@SpringBootTest
public class VerifyCodeTest {
    @Test
    public void testVCode(){
        System.out.println(VerifyTool.generateVerifyCode());
    }
}
