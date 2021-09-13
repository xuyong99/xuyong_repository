package com.java.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：11:37
 */
public interface SecKillMapper {

    /**
     * 修改商品的秒杀状态:未开始--->开始
     * @return
     */
    int updateSecKillProductStatus();

    /**
     * 查询参与秒杀的所有商品（但是商品还未还是）
     * @return
     */
    @Select("SELECT id,productId,num FROM web_seckill WHERE STATUS='0' AND startTime>=NOW()")
    List<Map<String,Object>> selectSecKillProduct();

    /**
     * 修改即将结束秒杀商品的状态
     * @return
     */
    int updateProductStarted2Finished();

    /**
     * 根据秒杀id查询秒杀商品的详细信息
     * @param secId
     * @return
     */
    @Select("SELECT * FROM web_seckill WHERE id=#{arg0}")
    Map<String,Object> selectSeckillProductInfo(Long secId);

}
