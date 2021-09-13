package com.java.service.impl;

import com.java.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：14:18
 */
@Service
@Transactional(readOnly = false)
public class BannerServiceImpl implements com.java.service.BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<Map<String,Object>> findBanners(){
        return bannerMapper.selectBanners();
    }

}
