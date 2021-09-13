package com.java.service.impl;

import com.java.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：09:23
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendData2MQ(Long secId, String uName) {
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("secId",secId);
        dataMap.put("uName",uName);
        rabbitTemplate.convertAndSend("ex-dingdan",null,dataMap);
    }
}
