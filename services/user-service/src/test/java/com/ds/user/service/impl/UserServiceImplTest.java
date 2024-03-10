package com.ds.user.service.impl;

import com.ds.user.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-09 19:04
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserServiceImplTest {
    @Resource
    private IUserServiceImpl userService;

    @Test
    public void testGetUserList(){
        userService.login(null);
    }
}
