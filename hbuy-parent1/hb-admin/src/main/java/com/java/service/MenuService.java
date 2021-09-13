package com.java.service;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：14:15
 */
public interface MenuService {
    List<Map<String,Object>> findHxMenus(Integer pageNum, Integer pageSize);
}
