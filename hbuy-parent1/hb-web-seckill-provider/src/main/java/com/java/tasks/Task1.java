package com.java.tasks;

import com.java.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * description：扫描哪些即将开始的秒杀商品
 * author：丁鹏
 * date：10:58
 */
@Component
public class Task1 {

    @Autowired
    private SecKillService secKillService;

    /**
     * 将开始的秒杀商品状态由未开始(0)修改为开始(1)
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void unStart2Start(){
        System.out.println("任务1.....将开始的秒杀商品状态由未开始(0)修改为开始(1)");
        secKillService.modifySeckKillInfo();
    }

    /**
     * 修改即将结束秒杀商品的状态
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void started2finished(){
        System.out.println("Task1.....修改即将结束秒杀商品的状态(由1改为2)");
        secKillService.modifySeckKillProductStarted2Finish();
    }

}
