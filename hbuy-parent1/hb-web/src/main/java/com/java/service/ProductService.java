package com.java.service;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：16:41
 */
public interface ProductService {
    List<Map<String,Object>> findProducts();

    List<String> findProductImgByPID(Long productId);
}
