package com.java.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * description：
 * author：丁鹏
 * date：10:40
 */
public class OrderUtils {

    private static final String[] digital = {"0","1","2","3","4","5","6","7","8","9"};

    /**
     * 生成唯一的订单编号
     * @param uName
     * @return
     * @throws Exception
     */
    public static String generateOrderNo(String uName) throws Exception {
        String orderStr = UUID.randomUUID().toString() +Math.random()+ uName;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(orderStr.getBytes("UTF-8"));
        StringBuffer orderNo = new StringBuffer();
        for (byte b : bytes){
            int temp = b;
            if(temp<0){
                temp=-temp;
            }
            int i = temp/16;//计算出范围在0-15之间的下标
            orderNo.append(digital[i]);
        }
        return orderNo.toString();
    }

    public static void main(String[] args) throws Exception {
        String orderNo = OrderUtils.generateOrderNo("zhangsan");
        System.out.println("orderNo="+orderNo);
    }

}
