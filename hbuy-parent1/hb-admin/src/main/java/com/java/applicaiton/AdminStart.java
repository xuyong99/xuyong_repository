package com.java.applicaiton;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description：
 * author：丁鹏
 * date：11:26
 */
//开启spring注解
@SpringBootApplication(scanBasePackages = "com.java.*")
//开启Eureka的客户端
@EnableEurekaClient
@Controller
@MapperScan("com.java.mapper")
public class AdminStart {
    public static void main(String[] args) {
        SpringApplication.run(AdminStart.class);
    }

    /**
     * 配置默认访问首页
     * @return
     */
    @RequestMapping("/")
    public String defaultPage(){
        return "redirect:/pages/Hplus-v.4.1.0/login_v2.jsp";
    }
}
