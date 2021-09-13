package com.java.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：14:17
 */
public interface BannerMapper {

    @Select("SELECT * FROM web_banner ORDER BY updateTime DESC LIMIT 6")
    List<Map<String,Object>> selectBanners();

}
