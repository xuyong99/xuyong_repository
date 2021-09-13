package com.java.service.impl;

import com.java.mapper.NavMapper;
import com.java.service.NavService;
import org.apache.ibatis.annotations.Select;
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
 * date：08:41
 */
@Service
@Transactional(readOnly = false)
public class NavServiceImpl implements NavService {

    @Autowired
    private NavMapper navMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询横向导航栏
     * @return
     */
    @Override
    public  List<Map<String,Object>> findHxNavs(){
        ValueOperations vop = redisTemplate.opsForValue();
        try {
            //1、判断Redis库中指定的key是否存在
            Object v = vop.get("hxNavs");
            if(v==null){//2、如果不存在，从mysql中查询，将查询的结果放入redis
                System.out.println("走....mysql");
                List<Map<String, Object>> navList = navMapper.selectHxNavs();
                vop.set("hxNavs",navList);
                //设置失效时间
                redisTemplate.expire("hxNavs",5, TimeUnit.MINUTES);
                return navList;
            }else{//3、如果存在则redis中取
                System.out.println("走...redis缓存");
                return (List<Map<String,Object>>)v;
            }
        } catch (Exception e) {
            e.printStackTrace();
            List<Map<String, Object>> navList = navMapper.selectHxNavs();
            vop.set("hxNavs",navList);
            //设置失效时间
            redisTemplate.expire("hxNavs",5, TimeUnit.MINUTES);
            return navList;
        }
    }

}
