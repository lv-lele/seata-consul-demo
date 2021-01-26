package com.yonyou.cloud.common.config;

import com.yonyou.cloud.common.interceptors.SeataFeignClientInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBeanConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new SeataFeignClientInterceptor();
    }
}
