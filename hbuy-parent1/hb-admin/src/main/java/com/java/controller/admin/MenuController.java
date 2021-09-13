package com.java.controller.admin;

import com.github.pagehelper.PageInfo;
import com.java.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：14:31
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 跳转到横向菜单页面
     * @return
     */
    @RequestMapping("/toHxMenuPage")
    public String toHxMenuPage(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               Model model){
        List<Map<String, Object>> hxMenuList = menuService.findHxMenus(pageNum, pageSize);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(hxMenuList);
        model.addAttribute("pageInfo",pageInfo);
        return "Hplus-v.4.1.0/hxMenuList.jsp";
    }
}
