package com.java.service.impl;

import com.java.mapper.ProductMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：16:40
 */
@Service
public class ProductServiceImpl implements com.java.service.ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询所有产品信息
     * @return
     */
    @Override
    public List<Map<String,Object>> findProducts(){
        return productMapper.selectProducts();
    }

    /**
     *根据产品id查询某个产品的素有图片
     * @param productId
     * @return
     */
    @Override
    public List<String> findProductImgByPID(Long productId){
        return productMapper.selectProductImgByPID(productId);
    }

}
