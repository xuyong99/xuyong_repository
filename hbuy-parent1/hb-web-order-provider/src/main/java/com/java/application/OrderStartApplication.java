package com.java.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * description：
 * author：丁鹏
 * date：10:35
 */
@SpringBootApplication(scanBasePackages = "com.java.*")
@EnableEurekaClient
@MapperScan(basePackages = "com.java.mapper")
@EnableDiscoveryClient
//@EnableCaching
//@EnableScheduling
public class OrderStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderStartApplication.class);
    }
}
