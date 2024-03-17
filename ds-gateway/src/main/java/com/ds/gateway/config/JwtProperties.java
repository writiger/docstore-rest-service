package com.ds.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.time.Duration;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-17 11:17
 */
@Data
@ConfigurationProperties(prefix = "ds.jwt")
public class JwtProperties {
    private Resource location;
    private String password;
    private String alias;
    private Duration tokenTTL = Duration.ofMinutes(10);
}
