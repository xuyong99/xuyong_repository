package com.java.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * description：
 * author：丁鹏
 * date：10:42
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka2Start {
    public static void main(String[] args) {
        SpringApplication.run(Eureka2Start.class);
    }
}
