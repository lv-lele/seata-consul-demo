package com.yonyou.cloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author scott
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.yonyou.cloud.common","com.yonyou.cloud"})
@EnableFeignClients(basePackages = {"com.yonyou.cloud.demo.feign"})
public class DemoServiceAApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoServiceAApplication.class, args);
    }
}
