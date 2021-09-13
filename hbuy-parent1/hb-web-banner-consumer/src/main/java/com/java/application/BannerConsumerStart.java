package com.java.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * description：
 * author：丁鹏
 * date：14:51
 */
@SpringBootApplication(scanBasePackages = "com.java.*")
@EnableEurekaClient
@EnableDiscoveryClient
public class BannerConsumerStart {
    public static void main(String[] args) {
        SpringApplication.run(BannerConsumerStart.class);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
