package com.java.service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

/**
 * description：
 * author：丁鹏
 * date：16:06
 */
public interface DiscussService {

    /**
     * 获取评论信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    MongoCursor<Document> findDisucuss(Integer pageNum, Integer pageSize);

}
