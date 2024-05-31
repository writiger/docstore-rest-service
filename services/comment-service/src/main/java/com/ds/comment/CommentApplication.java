package com.ds.comment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author writiger
 * @description
 * @create_at 2024-05-22 10:21
 */
@EnableFeignClients(basePackages = "com.ds.api.client")
@MapperScan("com.ds.comment.mapper")
@SpringBootApplication
public class CommentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentApplication.class);
    }
}
