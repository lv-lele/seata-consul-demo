package com.yonyou.cloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author scott
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.yonyou.cloud.common","com.yonyou.cloud"})
public class DemoServiceBApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoServiceBApplication.class, args);
    }
}
