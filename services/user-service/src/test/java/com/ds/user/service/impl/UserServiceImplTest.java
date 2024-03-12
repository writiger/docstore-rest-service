package com.ds.user.service.impl;

import com.ds.common.utils.ShortUUID;
import com.ds.user.domain.dto.RegisterFormDTO;
import com.ds.user.enums.UserLevel;
import com.ds.user.enums.UserStatus;
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

    @Test
    public void testAccountIsExist(){
        RegisterFormDTO registerFormDTO = new RegisterFormDTO();
        registerFormDTO.setAccount("root");
        userService.register(registerFormDTO);
    }

    @Test
    public void testRegister(){
        RegisterFormDTO user = new RegisterFormDTO();
        user.setAccount("admin");
        user.setEmail("admin@qq.com");
        user.setPassword1("123456");
        user.setPassword2("123456");
        user.setName("admin");
        user.setBelong("dlnu");
        user.setVerify("test");
        userService.register(user);
    }

}
