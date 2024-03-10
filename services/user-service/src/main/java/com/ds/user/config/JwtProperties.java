package com.ds.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.time.Duration;

/**
 * @author writiger
 * @description 读取jwt相关配置
 * @create_at 2024-03-10 20:30
 */
@Data
@ConfigurationProperties(prefix = "ds.jwt")
public class JwtProperties {
    private Resource location;
    private String password;
    private String alias;
    private Duration tokenTTL = Duration.ofMinutes(10);
}
