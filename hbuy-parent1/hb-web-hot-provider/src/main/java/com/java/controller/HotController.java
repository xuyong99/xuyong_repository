package com.java.controller;

import com.java.service.HotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：16:10
 */
@Controller
@RequestMapping("/hot")
public class HotController {

    @Autowired
    private HotService hotService;

    /**
     * 获取热门商品列表
     * @return
     */
    @RequestMapping("/getHotProducts")
    public @ResponseBody List<Map<String,Object>> getHotProducts(){
        return hotService.findProductList();
    }

}
