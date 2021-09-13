package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：14:45
 */
@Controller
@RequestMapping("/banner_consumer")
public class BannerController {

    @Autowired
    private RestTemplate restTemplate;
    /**
     * 轮播图获取消费者--->提供者
     * @return
     */
    @RequestMapping("/getBannersByConsumer")
    public @ResponseBody List<Map<String,Object>> getBannersByConsumer(){
        return restTemplate.getForObject("http://banner-provider/banner/getWebBanners",List.class);
    }

}
