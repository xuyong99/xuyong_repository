package com.java.service;

import org.apache.ibatis.annotations.Insert;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：15:48
 */
public interface BannerService {
    List<Map<String,Object>> findBanners(Integer pageNum, Integer pageSize);

    /**
     * 添加轮播图信息
     * @param paramMap
     * @return
     */
    boolean saveBanner(Map<String,Object> paramMap);
}
