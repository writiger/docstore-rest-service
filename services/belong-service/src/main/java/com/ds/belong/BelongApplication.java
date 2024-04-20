package com.ds.belong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author writiger
 * @description 所属服务启动类
 * @create_at 2024-04-20 13:45
 */
@EnableFeignClients(basePackages = "com.ds.api.client")
@MapperScan("com.ds.belong.mapper")
@SpringBootApplication
public class BelongApplication {
    public static void main(String[] args) {
        SpringApplication.run(BelongApplication.class);
    }
}
