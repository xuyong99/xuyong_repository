package com.java.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：08:38
 */
public interface NavMapper {

    /**
     * 查询横向导航栏
     * @return
     */
    @Select("SELECT * FROM web_menu WHERE menuType='1' LIMIT 8")
    List<Map<String,Object>> selectHxNavs();

}
