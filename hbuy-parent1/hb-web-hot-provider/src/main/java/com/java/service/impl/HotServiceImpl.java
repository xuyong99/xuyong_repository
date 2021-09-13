package com.java.service.impl;

import com.java.mapper.HotMapper;
import com.java.service.HotService;
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
 * date：16:03
 */
@Service
@Transactional(readOnly = false)
public class HotServiceImpl implements HotService{

    @Autowired
    private HotMapper hotMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Map<String, Object>> findProductList() {
        ValueOperations vop = redisTemplate.opsForValue();
        try {
            Object value = vop.get("hotProductList");
            if(value==null){//数据在redis中不存在
                //从mysql数据库中查询
                List<Map<String, Object>> productList = hotMapper.selectProductList();
                //存放到redis中去
                vop.set("hotProductList",productList);
                //设置失效时间
                redisTemplate.expire("hotProductList", 5,TimeUnit.MINUTES);
                return productList;
            }else{//数据在redis中存在
                return (List<Map<String, Object>>) value;
            }
        } catch (Exception e) {
            //从mysql数据库中查询
            List<Map<String, Object>> productList = hotMapper.selectProductList();
            //存放到redis中去
            vop.set("hotProductList",productList);
            //设置失效时间
            redisTemplate.expire("hotProductList", 5,TimeUnit.MINUTES);
            return productList;
        }
    }

}
