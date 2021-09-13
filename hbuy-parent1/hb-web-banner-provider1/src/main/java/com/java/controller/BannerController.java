package com.java.controller;

import com.java.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：14:20
 */
@Controller
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/getWebBanners")
    public @ResponseBody List<Map<String,Object>> getWebBanners(){
        Map<String,Object> map1 = new HashMap<>();
        map1.put("aaa","aaaa");
        //return Arrays.asList(map1);
        return bannerService.findBanners();
    }

}
