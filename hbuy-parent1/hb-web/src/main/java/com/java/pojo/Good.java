package com.java.pojo;

import java.io.Serializable;

/**
 * description：商品
 * author：丁鹏
 * date：11:13
 */
public class Good implements Serializable{

    private static final long serialVersionUID = -2427426994450011577L;
    private String productNum;//商品编号
    private Integer num;//商品购买数量

    public Good() {
    }

    public Good(String productNum, Integer num) {
        this.productNum = productNum;
        this.num = num;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Good{" +
                "productNum='" + productNum + '\'' +
                ", num=" + num +
                '}';
    }
}
