package com.java.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：16:01
 */
public interface HotMapper {

    /**
     * 查询商品列表
     * @return
     */
    @Select("SELECT wpd.`title`,wpd.`subTitle`,wpd.`price`,wpd.`href`,wpi.`imgUrl` \n" +
            "FROM web_product_detail wpd INNER JOIN `web_product_img` wpi\n" +
            "ON wpd.`id`=wpi.`productId` GROUP BY wpd.`id`")
    List<Map<String,Object>> selectProductList();

}
