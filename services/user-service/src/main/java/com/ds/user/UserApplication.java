package com.ds.user;

import com.alibaba.nacos.api.annotation.NacosProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author writiger
 * @description 用户服务启动类
 * @create_at 2024-03-09 17:11
 */
@MapperScan("com.ds.user.mapper")
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}
