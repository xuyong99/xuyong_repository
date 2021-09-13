package com.java.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：16:37
 */
public interface ProductMapper {

    /**
     * 查询所有产品信息
     * @return
     */
    @Select("SELECT * FROM web_product_detail")
    List<Map<String,Object>> selectProducts();

    /**
     *根据产品id查询某个产品的素有图片
     * @param productId
     * @return
     */
    @Select("SELECT imgUrl FROM web_product_img WHERE productId=#{arg0}")
    List<String> selectProductImgByPID(Long productId);

}
