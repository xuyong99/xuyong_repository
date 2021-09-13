package com.java.service;

import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：11:56
 */
public interface SecKillService {
    void modifySeckKillInfo();

    void modifySeckKillProductStarted2Finish();

    /**
     * 根据秒杀id查询秒杀商品的详细信息
     * @param secId
     * @return
     */
    Map<String,Object> findtSeckillProductInfo(Long secId);
}
