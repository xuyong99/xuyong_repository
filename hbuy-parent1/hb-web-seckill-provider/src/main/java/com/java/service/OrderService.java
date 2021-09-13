package com.java.service;

/**
 * description：秒杀模块往RabbitMQ中的队列中发送数据
 * author：丁鹏
 * date：09:21
 */
public interface OrderService {

    void sendData2MQ(Long secId,String uName);

}
