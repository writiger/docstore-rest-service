package com.ds.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-17 11:15
 */
@Data
@Component
@ConfigurationProperties(prefix = "ds.auth")
public class AuthProperties {
    private List<String> includePaths;
    private List<String> excludePaths;
}
