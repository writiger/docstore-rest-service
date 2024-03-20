package com.ds.user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ds.common.domain.query.PageQuery;
import com.ds.user.domain.dto.RegisterFormDTO;
import com.ds.user.domain.po.User;
import com.ds.user.service.IAdminService;
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
    @Resource
    private IAdminService adminService;

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

    @Test
    public void testUserList(){
        PageQuery query = new PageQuery();
        query.setPageSize(2);
        query.setPageNo(1);
        Page<User> userList = adminService.lambdaQuery().ne(User::getLevel,2).page(query.toMpPage());
        for(User user:userList.getRecords()){
            System.out.println(user);
        }
    }
}
