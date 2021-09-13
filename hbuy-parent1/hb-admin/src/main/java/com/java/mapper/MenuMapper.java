package com.java.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：14:12
 */
public interface MenuMapper {

    /**
     * 查询横向导航栏
     * @return
     */
    @Select("SELECT * FROM web_menu WHERE menuType='1'")
    List<Map<String,Object>> selectHxMenus();

}
