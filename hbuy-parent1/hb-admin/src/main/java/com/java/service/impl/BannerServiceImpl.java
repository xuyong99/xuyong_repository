package com.java.service.impl;

import com.github.pagehelper.PageHelper;
import com.java.mapper.BannerMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：15:46
 */
@Service
@Transactional(readOnly = false)
public class BannerServiceImpl implements com.java.service.BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 动态获取轮播图
     * @return
     */
    @Override
    public List<Map<String,Object>> findBanners(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return bannerMapper.selectBanners();
    }

    @Override
    public boolean saveBanner(Map<String, Object> paramMap) {
        return bannerMapper.insertBanner(paramMap)==1;
    }

}
