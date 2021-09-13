package com.java.service.impl;

import com.java.mapper.SecKillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：11:43
 */
@Service
@Transactional(readOnly = false)
public class SecKillServiceImpl implements com.java.service.SecKillService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SecKillMapper secKillMapper;

    /**
     * 1、将未开始参与秒杀的商品状态改为正则进行秒杀的状态
     * 2、将参与秒杀商品的相关信息存放到redis中去
     */
    @Override
    public void modifySeckKillInfo(){
        //修改开始秒杀商品的状态
       secKillMapper.updateSecKillProductStatus();
        //将即将开始秒杀商品的秒杀id和数量等信息存放到redis中去
        List<Map<String, Object>> productList = secKillMapper.selectSecKillProduct();
        //获取list类型的redis帮助类
        ListOperations vopList =  redisTemplate.opsForList();
        for(int i = 0;productList!=null && productList.size()>=1 && i<productList.size();i++){
            //获取秒杀表中的主键
            Long secId = (Long) productList.get(i).get("id");
            //获取秒杀名额
            Integer num = (Integer) productList.get(i).get("num");
            //插入数据前先判断redis中是否存在
            boolean flag = redisTemplate.hasKey("seckill_products_" + secId);
            if(!flag){
                //将指定秒杀名额的商品对应的秒杀id存放到redis中去
                for(int j=0;j<num;j++){
                    vopList.leftPush("seckill_products_"+secId,secId);
                }
            }
        }
    }

    @Override
    public void modifySeckKillProductStarted2Finish() {
        secKillMapper.updateProductStarted2Finished();
    }

    @Override
    public Map<String, Object> findtSeckillProductInfo(Long secId) {
        return secKillMapper.selectSeckillProductInfo(secId);
    }

}
