package com.ds.api.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author writiger
 * @description
 * @create_at 2024-04-20 17:59
 */

public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
