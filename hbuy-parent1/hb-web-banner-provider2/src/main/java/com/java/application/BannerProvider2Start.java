package com.java.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * description：
 * author：丁鹏
 * date：14:39
 */
@SpringBootApplication(scanBasePackages = "com.java.*")
@EnableEurekaClient
@MapperScan(basePackages = "com.java.mapper")
@EnableDiscoveryClient
public class BannerProvider2Start {
    public static void main(String[] args) {
        SpringApplication.run(BannerProvider2Start.class);
    }
}
