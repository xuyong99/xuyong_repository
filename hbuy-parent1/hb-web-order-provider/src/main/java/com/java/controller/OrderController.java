package com.java.controller;

import com.java.utils.OrderUtils;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * description：从RabbitMQ中获取数据，处理订单
 * author：丁鹏
 * date：10:37
 */
@Component
public class OrderController {

    @RabbitListener(bindings = @QueueBinding(
            value=@Queue(value = "queue-dingdan"),
            exchange = @Exchange(value = "ex-dingdan",type = "fanout")
    ))
    @RabbitHandler
    public void handMQData(@Payload Map<String,Object> dataMap, Channel channel,@Headers Map<String,Object> headers) throws Exception {
        //1、从RabbitMQ中获取数据
        Long secId = (Long) dataMap.get("secId");
        String uName = (String) dataMap.get("uName");
        //2、安全监测
        System.out.println("OrderController.....安全监测");
        //3、生成订单编号
        String orderNo = OrderUtils.generateOrderNo(uName);
        //将订单编号、订单状态、订单金额、秒杀id、uName存放到web_orders表中
        System.out.println("OrderController....订单已经被创建");
        //4、支付宝支付
        Thread.sleep(3000);
        System.out.println(uName+"....支付成功");
        //5、手动确认订单处理完毕
        long endTag = (long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(endTag,false);
    }

}
