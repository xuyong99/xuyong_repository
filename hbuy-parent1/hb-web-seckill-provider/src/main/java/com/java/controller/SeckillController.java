package com.java.controller;

import cn.hutool.core.util.StrUtil;
import com.java.service.OrderService;
import com.java.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * description：
 * author：丁鹏
 * date：09:20
 */
@Controller
public class SeckillController {
    @Autowired
    private SecKillService secKillService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/login")
    public @ResponseBody String login(String uName,HttpSession session){
        session.setAttribute("uName",uName);
        return "login success";
    }

    @RequestMapping("/doSecKill")
    //public @ResponseBody Map<String,Object> doSecKill(Long secId, HttpSession session){
    public @ResponseBody Map<String,Object> doSecKill(Long secId, String uName){
        
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0");//秒杀成功
        try {
            //Object uName = session.getAttribute("uName");
            if(uName==null || StrUtil.isBlank((String)uName)){
                resultMap.put("status","1");//秒杀失败
                resultMap.put("msg","您还未登录");
                return resultMap;
            }
            //查询指定秒杀id商品的详细信息
            Map<String, Object> secKillProductMap = secKillService.findtSeckillProductInfo(secId);
            if (secKillProductMap==null || secKillProductMap.size()==0){
                resultMap.put("status","1");//秒杀失败
                resultMap.put("msg","秒杀商品不存在");
                return resultMap;
            }
            //取出秒杀商品的状态
            Object status = secKillProductMap.get("status");
            //秒杀还未开始
            if("0".equals(status)){
                resultMap.put("status","1");//秒杀失败
                resultMap.put("msg","秒杀还未开始");
                return resultMap;
            }
            //秒杀结束
            if("2".equals(status)){
                resultMap.put("status","1");//秒杀失败
                resultMap.put("msg","秒杀已经结束，下次再来吧");
                return resultMap;
            }
            //开始秒杀
            if("1".equals(status)){
                //获取list集合的redis工具类
                ListOperations vopList = redisTemplate.opsForList();
                //从list集合中移除一个商品
                Object value = vopList.leftPop("seckill_products_" + secId);
                //判断秒杀名额是否还有
                if(value==null){//秒杀名额完了
                    resultMap.put("status","1");//秒杀失败
                    resultMap.put("msg","秒杀已经别抢购完，下次再来吧");
                    return resultMap;
                }else{//还有秒杀名额
                    //判断某个用户是否重复抢购
                    SetOperations vopSet = redisTemplate.opsForSet();
                    boolean flag = vopSet.isMember("seckill_users_" + secId, uName);
                    if(flag){//此用户已经抢购过
                        resultMap.put("status","1");//秒杀失败
                        resultMap.put("msg","不能重复抢购,秒杀每人只能拥有一个名额");
                        //重新往list集合汇总添加一个商品
                        vopList.leftPush("seckill_products_" + secId, secId);
                        return resultMap;
                    }else{//此用户没有抢购
                        vopSet.add("seckill_users_"+secId,uName);
                        //往RabbitMQ中对应的消息队列中存放相关数据
                        orderService.sendData2MQ(secId,uName);
                        return resultMap;
                    }
                }
            }
            resultMap.put("status","1");//抢购失败
            resultMap.put("msg","未知错误");
            return resultMap;
        } catch (Exception e) {
            resultMap.put("status","1");//抢购失败
            resultMap.put("msg","目前参与秒杀人员过多，系统崩溃");
            return resultMap;
        }
    }

}
