package com.java.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * description：
 * author：丁鹏
 * date：08:27
 */
@SpringBootApplication(scanBasePackages = "com.java.*")
@EnableEurekaClient
@MapperScan("com.java.mapper")
//开启缓存
@EnableCaching
@EnableDiscoveryClient
@EnableScheduling
//扫描过虑器组件
@ServletComponentScan(basePackages = "com.java.filters")
public class WebStart {
    public static void main(String[] args) {
        SpringApplication.run(WebStart.class);
    }

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
