package com.java.service.impl;

import com.java.service.DiscussService;
import com.java.utils.MongoUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.stereotype.Service;

/**
 * description：
 * author：丁鹏
 * date：16:06
 */
@Service
public class DiscussServiceImpl implements DiscussService{
    //private static MongoCollection<Document> collection = MongoUtil.getCollection();

    @Override
    public MongoCursor<Document> findDisucuss(Integer pageNum, Integer pageSize) {
        try {
            //获取MongoDB的连接
            MongoCollection<Document> collection = MongoUtil.getCollection();
            //计算分页相关参数
            int startIndex = (pageNum-1)*pageSize;
            Document doc1 = Document.parse("{\"createDate\":-1}");
            FindIterable<Document> docs = collection.find().sort(doc1).skip(startIndex).limit(pageSize);
            //FindIterable<Document> docs = collection.find();
            MongoCursor<Document> iterator = docs.iterator();
            return iterator;
        } catch (Exception e) {
            return null;
        } finally {
            MongoUtil.close();
        }
    }
}
