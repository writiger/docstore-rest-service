package com.ds.user.passwd;

import com.ds.common.exception.BadRequestException;
import org.apache.catalina.security.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-10 21:23
 */
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class PasswdTest {
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Test
    public void encodePasswd(){
        System.out.println("--------HERE--------");
        System.out.println(passwordEncoder.encode("123456"));
        System.out.println("--------HERE--------");
    }

    @Test
    public void decodePasswd(){
        Boolean res = passwordEncoder.matches(
                "123456","$2a$10$yMlNkqH3kK2Txyh.RVNU1.PEYe0MjEF485oTrMyGSvBuEV51rWgAW");
        assertEquals(true, res);
    }
}
