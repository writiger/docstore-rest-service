package com.ds.doc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author writiger
 * @description 文献服务启动类
 * @create_at 2024-04-27 13:01
 */
@EnableFeignClients(basePackages = "com.ds.api.client")
@MapperScan("com.ds.doc.mapper")
@SpringBootApplication
public class DocApplication {
    public static void main(String[] args) {
        SpringApplication.run(DocApplication.class);
    }
}
