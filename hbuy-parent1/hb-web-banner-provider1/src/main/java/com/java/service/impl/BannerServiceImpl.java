package com.java.service.impl;

import com.java.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Map<String,Object>> findBanners(){
        ValueOperations vo = redisTemplate.opsForValue();
        try {
            //webBanners=List<Map<String,Object>>
            Object value = vo.get("webBanners");
            if(value==null){//redis中不存在
                List<Map<String,Object>> bannerList = bannerMapper.selectBanners();
                vo.set("webBanners",bannerList);
                redisTemplate.expire("webBanners",5, TimeUnit.MINUTES);
                return bannerList;
            }else{//redis中存在
                return (List<Map<String, Object>>) value;
            }
        } catch (Exception e) {
            List<Map<String,Object>> bannerList = bannerMapper.selectBanners();
            vo.set("webBanners",bannerList);
            redisTemplate.expire("webBanners",5, TimeUnit.MINUTES);
            return bannerList;
        }
    }

}
