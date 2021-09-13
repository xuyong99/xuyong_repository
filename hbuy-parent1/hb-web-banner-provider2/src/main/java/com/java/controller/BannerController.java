package com.java.controller;

import com.java.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return bannerService.findBanners();
    }

}
