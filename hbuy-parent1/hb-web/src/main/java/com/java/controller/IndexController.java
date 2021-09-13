package com.java.controller;

import com.java.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：08:44
 */
@Controller
@Scope("prototype")
public class IndexController {
    @Autowired
    private NavService navService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 转发到Index.jsp页面
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String toIndex(Model model){
        //获取横向导航栏
        List<Map<String, Object>> hxNavList = navService.findHxNavs();
        //获取轮播
        //List<Map<String,Object>> bannerList = restTemplate.getForObject("http://bannerConsumer/banner_consumer/getBannersByConsumer", List.class);
        //获取热门商品
       List<Map<String,Object>> hotProductList = restTemplate.getForObject("http://hotProvider/hot/getHotProducts", List.class);
        model.addAttribute("hxNavList",hxNavList);
       // model.addAttribute("bannerList",bannerList);
        model.addAttribute("hotProductList",hotProductList);
        return "Index.jsp";
    }

    /**
     * 测试
     * @return
     */
    @RequestMapping("/test")
    public @ResponseBody List<Map<String,Object>> toIndex(){
        return navService.findHxNavs();
    }


}
