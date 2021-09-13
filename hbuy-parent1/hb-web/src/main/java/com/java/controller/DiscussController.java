package com.java.controller;

import com.java.service.DiscussService;
import com.java.utils.MongoUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * description：
 * author：丁鹏
 * date：16:03
 */
@Controller
@RequestMapping("/discuss")
public class DiscussController {

    @Autowired
    private DiscussService discussService;

    /**
     * 获取评论
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getDiscuss")
    public @ResponseBody
        List<Document> getDiscuss(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize){
        MongoCursor<Document> disucuss = discussService.findDisucuss(pageNum, pageSize);
        List<Document> docList = new ArrayList<>();
        disucuss.forEachRemaining(temp-> System.out.println(docList.add(temp)));
        return docList;
    }

}
