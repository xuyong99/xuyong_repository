package com.java.controller.admin;

import com.github.pagehelper.PageInfo;
import com.java.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：15:43
 */
@Controller
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;


    /**
     * 跳转到横向菜单页面
     * @return
     */
    @RequestMapping("/toBannerPage")
    public String toBannerPage(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               Model model){
        List<Map<String, Object>> bannerList = bannerService.findBanners(pageNum,pageSize);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(bannerList);
        model.addAttribute("pageInfo",pageInfo);
        return "Hplus-v.4.1.0/bannerList.jsp";
    }

    /**
     * 添加轮播图信息
     * @param paramMap
     * @return
     */
    @RequestMapping("/addBanner")
    public @ResponseBody boolean addBanner(@RequestParam Map<String,Object> paramMap){
        System.out.println("paramMap="+paramMap);
        return bannerService.saveBanner(paramMap);
    }

}
