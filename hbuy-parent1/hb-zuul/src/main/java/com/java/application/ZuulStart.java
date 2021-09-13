package com.java.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * description：
 * author：丁鹏
 * date：09:12
 */
@SpringBootApplication
@EnableEurekaClient
//开启网关代理
@EnableZuulProxy
public class ZuulStart {
    public static void main(String[] args) {
        SpringApplication.run(ZuulStart.class);
    }
}
