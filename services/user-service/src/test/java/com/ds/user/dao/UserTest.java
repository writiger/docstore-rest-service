package com.ds.user.dao;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.ds.user.domain.po.User;
import com.ds.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-09 19:39
 */
@SpringBootTest
@ActiveProfiles("test")
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}